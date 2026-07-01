----------------------------------------------------------
-- Create PACKAGE Specification purchase_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE purchase_pkg AS

    PROCEDURE create_purchase (
        p_supplier_id   IN purchase_headers.supplier_id%TYPE,
        p_warehouse_id  IN purchase_headers.warehouse_id%TYPE,
        p_invoice_no    IN purchase_headers.invoice_no%TYPE,
        p_purchase_id   OUT purchase_headers.purchase_id%TYPE
    );

    PROCEDURE add_purchase_line (
        p_purchase_id IN purchase_lines.purchase_id%TYPE,
        p_product_id  IN purchase_lines.product_id%TYPE,
        p_quantity    IN purchase_lines.quantity%TYPE,
        p_unit_cost   IN purchase_lines.unit_cost%TYPE
    );

    PROCEDURE confirm_purchase (
        p_purchase_id IN purchase_headers.purchase_id%TYPE
    );

    PROCEDURE get_purchase_by_id (
        p_purchase_id IN purchase_headers.purchase_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    );

END purchase_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY purchase_pkg
----------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY purchase_pkg AS

    PROCEDURE create_purchase (
        p_supplier_id   IN purchase_headers.supplier_id%TYPE,
        p_warehouse_id  IN purchase_headers.warehouse_id%TYPE,
        p_invoice_no    IN purchase_headers.invoice_no%TYPE,
        p_purchase_id   OUT purchase_headers.purchase_id%TYPE
    ) AS
    BEGIN
        INSERT INTO purchase_headers (
            supplier_id,
            warehouse_id,
            invoice_no
        ) VALUES (
            p_supplier_id,
            p_warehouse_id,
            p_invoice_no
        )
        RETURNING purchase_id INTO p_purchase_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20601, 'Invoice number already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20699, 'Error creating purchase: ' || SQLERRM);
    END create_purchase;


    PROCEDURE add_purchase_line (
        p_purchase_id IN purchase_lines.purchase_id%TYPE,
        p_product_id  IN purchase_lines.product_id%TYPE,
        p_quantity    IN purchase_lines.quantity%TYPE,
        p_unit_cost   IN purchase_lines.unit_cost%TYPE
    ) AS
        v_status purchase_headers.status%TYPE;
        v_line_total NUMBER(12,2);
    BEGIN
        SELECT status
        INTO v_status
        FROM purchase_headers
        WHERE purchase_id = p_purchase_id;

        IF v_status <> 'DRAFT' THEN
            RAISE_APPLICATION_ERROR(-20602, 'Purchase line can only be added to DRAFT purchase.');
        END IF;

        v_line_total := p_quantity * p_unit_cost;

        INSERT INTO purchase_lines (
            purchase_id,
            product_id,
            quantity,
            unit_cost,
            line_total
        ) VALUES (
            p_purchase_id,
            p_product_id,
            p_quantity,
            p_unit_cost,
            v_line_total
        );

        UPDATE purchase_headers
        SET total_amount = total_amount + v_line_total,
            updated_at   = SYSTIMESTAMP
        WHERE purchase_id = p_purchase_id;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20603, 'Purchase header not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20699, 'Error adding purchase line: ' || SQLERRM);
    END add_purchase_line;


    PROCEDURE confirm_purchase (
        p_purchase_id IN purchase_headers.purchase_id%TYPE
    ) AS
        v_status       purchase_headers.status%TYPE;
        v_warehouse_id purchase_headers.warehouse_id%TYPE;
    BEGIN
        SELECT status, warehouse_id
        INTO v_status, v_warehouse_id
        FROM purchase_headers
        WHERE purchase_id = p_purchase_id
        FOR UPDATE;

        IF v_status <> 'DRAFT' THEN
            RAISE_APPLICATION_ERROR(-20604, 'Only DRAFT purchase can be confirmed.');
        END IF;

        FOR r IN (
            SELECT product_id, quantity
            FROM purchase_lines
            WHERE purchase_id = p_purchase_id
        ) LOOP
            inventory_pkg.increase_stock(
                p_product_id       => r.product_id,
                p_warehouse_id     => v_warehouse_id,
                p_quantity         => r.quantity,
                p_transaction_type => 'PURCHASE',
                p_reference_no     => 'PURCHASE-' || p_purchase_id,
                p_remarks          => 'Purchase confirmed'
            );
        END LOOP;

        UPDATE purchase_headers
        SET status     = 'CONFIRMED',
            updated_at = SYSTIMESTAMP
        WHERE purchase_id = p_purchase_id;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20603, 'Purchase header not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20699, 'Error confirming purchase: ' || SQLERRM);
    END confirm_purchase;


    PROCEDURE get_purchase_by_id (
        p_purchase_id IN purchase_headers.purchase_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT ph.purchase_id,
                   ph.supplier_id,
                   s.supplier_name,
                   ph.warehouse_id,
                   w.warehouse_name,
                   ph.purchase_date,
                   ph.invoice_no,
                   ph.total_amount,
                   ph.status,
                   pl.purchase_line_id,
                   pl.product_id,
                   p.product_name,
                   pl.quantity,
                   pl.unit_cost,
                   pl.line_total
            FROM purchase_headers ph
            JOIN suppliers s ON ph.supplier_id = s.supplier_id
            JOIN warehouses w ON ph.warehouse_id = w.warehouse_id
            LEFT JOIN purchase_lines pl ON ph.purchase_id = pl.purchase_id
            LEFT JOIN products p ON pl.product_id = p.product_id
            WHERE ph.purchase_id = p_purchase_id
            ORDER BY pl.purchase_line_id;

    END get_purchase_by_id;

END purchase_pkg;
/
