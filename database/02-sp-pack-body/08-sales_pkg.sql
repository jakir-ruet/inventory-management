----------------------------------------------------------
-- Create PACKAGE Specification sales_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE sales_pkg AS

    PROCEDURE create_sales (
        p_buyer_id     IN sales_headers.buyer_id%TYPE,
        p_warehouse_id IN sales_headers.warehouse_id%TYPE,
        p_invoice_no   IN sales_headers.invoice_no%TYPE,
        p_sales_id     OUT sales_headers.sales_id%TYPE
    );

    PROCEDURE add_sales_line (
        p_sales_id   IN sales_lines.sales_id%TYPE,
        p_product_id IN sales_lines.product_id%TYPE,
        p_quantity   IN sales_lines.quantity%TYPE,
        p_unit_price IN sales_lines.unit_price%TYPE
    );

    PROCEDURE confirm_sales (
        p_sales_id IN sales_headers.sales_id%TYPE
    );

    PROCEDURE get_sales_by_id (
        p_sales_id IN sales_headers.sales_id%TYPE,
        p_result   OUT SYS_REFCURSOR
    );

END sales_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY sales_pkg
----------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY sales_pkg AS

    PROCEDURE create_sales (
        p_buyer_id     IN sales_headers.buyer_id%TYPE,
        p_warehouse_id IN sales_headers.warehouse_id%TYPE,
        p_invoice_no   IN sales_headers.invoice_no%TYPE,
        p_sales_id     OUT sales_headers.sales_id%TYPE
    ) AS
    BEGIN
        INSERT INTO sales_headers (
            buyer_id,
            warehouse_id,
            invoice_no
        ) VALUES (
            p_buyer_id,
            p_warehouse_id,
            p_invoice_no
        )
        RETURNING sales_id INTO p_sales_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20701, 'Sales invoice number already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20799, 'Error creating sales: ' || SQLERRM);
    END create_sales;


    PROCEDURE add_sales_line (
        p_sales_id   IN sales_lines.sales_id%TYPE,
        p_product_id IN sales_lines.product_id%TYPE,
        p_quantity   IN sales_lines.quantity%TYPE,
        p_unit_price IN sales_lines.unit_price%TYPE
    ) AS
        v_status     sales_headers.status%TYPE;
        v_line_total NUMBER(12,2);
    BEGIN
        SELECT status
        INTO v_status
        FROM sales_headers
        WHERE sales_id = p_sales_id;

        IF v_status <> 'DRAFT' THEN
            RAISE_APPLICATION_ERROR(-20702, 'Sales line can only be added to DRAFT sales.');
        END IF;

        v_line_total := p_quantity * p_unit_price;

        INSERT INTO sales_lines (
            sales_id,
            product_id,
            quantity,
            unit_price,
            line_total
        ) VALUES (
            p_sales_id,
            p_product_id,
            p_quantity,
            p_unit_price,
            v_line_total
        );

        UPDATE sales_headers
        SET total_amount = total_amount + v_line_total,
            updated_at   = SYSTIMESTAMP
        WHERE sales_id = p_sales_id;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20703, 'Sales header not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20799, 'Error adding sales line: ' || SQLERRM);
    END add_sales_line;


    PROCEDURE confirm_sales (
        p_sales_id IN sales_headers.sales_id%TYPE
    ) AS
        v_status       sales_headers.status%TYPE;
        v_warehouse_id sales_headers.warehouse_id%TYPE;
    BEGIN
        SELECT status, warehouse_id
        INTO v_status, v_warehouse_id
        FROM sales_headers
        WHERE sales_id = p_sales_id
        FOR UPDATE;

        IF v_status <> 'DRAFT' THEN
            RAISE_APPLICATION_ERROR(-20704, 'Only DRAFT sales can be confirmed.');
        END IF;

        FOR r IN (
            SELECT product_id, quantity
            FROM sales_lines
            WHERE sales_id = p_sales_id
        ) LOOP
            inventory_pkg.decrease_stock(
                p_product_id       => r.product_id,
                p_warehouse_id     => v_warehouse_id,
                p_quantity         => r.quantity,
                p_transaction_type => 'SALE',
                p_reference_no     => 'SALE-' || p_sales_id,
                p_remarks          => 'Sales confirmed'
            );
        END LOOP;

        UPDATE sales_headers
        SET status     = 'CONFIRMED',
            updated_at = SYSTIMESTAMP
        WHERE sales_id = p_sales_id;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20703, 'Sales header not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20799, 'Error confirming sales: ' || SQLERRM);
    END confirm_sales;


    PROCEDURE get_sales_by_id (
        p_sales_id IN sales_headers.sales_id%TYPE,
        p_result   OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT sh.sales_id,
                   sh.buyer_id,
                   b.buyer_name,
                   sh.warehouse_id,
                   w.warehouse_name,
                   sh.sales_date,
                   sh.invoice_no,
                   sh.total_amount,
                   sh.status,
                   sl.sales_line_id,
                   sl.product_id,
                   p.product_name,
                   sl.quantity,
                   sl.unit_price,
                   sl.line_total
            FROM sales_headers sh
            JOIN buyers b ON sh.buyer_id = b.buyer_id
            JOIN warehouses w ON sh.warehouse_id = w.warehouse_id
            LEFT JOIN sales_lines sl ON sh.sales_id = sl.sales_id
            LEFT JOIN products p ON sl.product_id = p.product_id
            WHERE sh.sales_id = p_sales_id
            ORDER BY sl.sales_line_id;

    END get_sales_by_id;

END sales_pkg;
/
