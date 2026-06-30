----------------------------------------------------------
-- Create PACKAGE Specification inventory_pkg
----------------------------------------------------------
-- 1. Create/initialize stock
-- 2. Increase stock
-- 3. Decrease stock
-- 4. Adjust stock
-- 5. Record stock transaction
-- 6. View current stock

CREATE OR REPLACE PACKAGE inventory_pkg AS

    PROCEDURE initialize_stock (
        p_product_id   IN stock.product_id%TYPE,
        p_warehouse_id IN stock.warehouse_id%TYPE,
        p_quantity     IN stock.quantity%TYPE
    );

    PROCEDURE increase_stock (
        p_product_id       IN stock.product_id%TYPE,
        p_warehouse_id     IN stock.warehouse_id%TYPE,
        p_quantity         IN stock.quantity%TYPE,
        p_transaction_type IN stock_transactions.transaction_type%TYPE,
        p_reference_no     IN stock_transactions.reference_no%TYPE,
        p_remarks          IN stock_transactions.remarks%TYPE
    );

    PROCEDURE decrease_stock (
        p_product_id       IN stock.product_id%TYPE,
        p_warehouse_id     IN stock.warehouse_id%TYPE,
        p_quantity         IN stock.quantity%TYPE,
        p_transaction_type IN stock_transactions.transaction_type%TYPE,
        p_reference_no     IN stock_transactions.reference_no%TYPE,
        p_remarks          IN stock_transactions.remarks%TYPE
    );

    PROCEDURE get_stock_by_product (
        p_product_id IN stock.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_stock (
        p_result OUT SYS_REFCURSOR
    );

    PROCEDURE get_stock_transactions (
        p_product_id IN stock_transactions.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    );

END inventory_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY inventory_pkg
----------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY inventory_pkg AS

    PROCEDURE initialize_stock (
        p_product_id   IN stock.product_id%TYPE,
        p_warehouse_id IN stock.warehouse_id%TYPE,
        p_quantity     IN stock.quantity%TYPE
    ) AS
    BEGIN
        INSERT INTO stock (
            product_id,
            warehouse_id,
            quantity
        ) VALUES (
            p_product_id,
            p_warehouse_id,
            p_quantity
        );

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20501, 'Stock already exists for this product and warehouse.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20599, 'Error initializing stock: ' || SQLERRM);
    END initialize_stock;


    PROCEDURE increase_stock (
        p_product_id       IN stock.product_id%TYPE,
        p_warehouse_id     IN stock.warehouse_id%TYPE,
        p_quantity         IN stock.quantity%TYPE,
        p_transaction_type IN stock_transactions.transaction_type%TYPE,
        p_reference_no     IN stock_transactions.reference_no%TYPE,
        p_remarks          IN stock_transactions.remarks%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        IF p_quantity <= 0 THEN
            RAISE_APPLICATION_ERROR(-20502, 'Quantity must be greater than zero.');
        END IF;

        SELECT COUNT(*)
        INTO v_count
        FROM stock
        WHERE product_id = p_product_id
          AND warehouse_id = p_warehouse_id;

        IF v_count = 0 THEN
            INSERT INTO stock (
                product_id,
                warehouse_id,
                quantity
            ) VALUES (
                p_product_id,
                p_warehouse_id,
                p_quantity
            );
        ELSE
            UPDATE stock
            SET quantity   = quantity + p_quantity,
                updated_at = SYSTIMESTAMP
            WHERE product_id = p_product_id
              AND warehouse_id = p_warehouse_id;
        END IF;

        INSERT INTO stock_transactions (
            product_id,
            warehouse_id,
            transaction_type,
            quantity,
            reference_no,
            remarks
        ) VALUES (
            p_product_id,
            p_warehouse_id,
            p_transaction_type,
            p_quantity,
            p_reference_no,
            p_remarks
        );

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20599, 'Error increasing stock: ' || SQLERRM);
    END increase_stock;


    PROCEDURE decrease_stock (
        p_product_id       IN stock.product_id%TYPE,
        p_warehouse_id     IN stock.warehouse_id%TYPE,
        p_quantity         IN stock.quantity%TYPE,
        p_transaction_type IN stock_transactions.transaction_type%TYPE,
        p_reference_no     IN stock_transactions.reference_no%TYPE,
        p_remarks          IN stock_transactions.remarks%TYPE
    ) AS
        v_current_qty stock.quantity%TYPE;
    BEGIN
        IF p_quantity <= 0 THEN
            RAISE_APPLICATION_ERROR(-20502, 'Quantity must be greater than zero.');
        END IF;

        SELECT quantity
        INTO v_current_qty
        FROM stock
        WHERE product_id = p_product_id
          AND warehouse_id = p_warehouse_id
        FOR UPDATE;

        IF v_current_qty < p_quantity THEN
            RAISE_APPLICATION_ERROR(-20503, 'Insufficient stock.');
        END IF;

        UPDATE stock
        SET quantity   = quantity - p_quantity,
            updated_at = SYSTIMESTAMP
        WHERE product_id = p_product_id
          AND warehouse_id = p_warehouse_id;

        INSERT INTO stock_transactions (
            product_id,
            warehouse_id,
            transaction_type,
            quantity,
            reference_no,
            remarks
        ) VALUES (
            p_product_id,
            p_warehouse_id,
            p_transaction_type,
            p_quantity,
            p_reference_no,
            p_remarks
        );

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20504, 'Stock record not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20599, 'Error decreasing stock: ' || SQLERRM);
    END decrease_stock;


    PROCEDURE get_stock_by_product (
        p_product_id IN stock.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT s.stock_id,
                   s.product_id,
                   p.product_name,
                   s.warehouse_id,
                   w.warehouse_name,
                   s.quantity,
                   s.created_at,
                   s.updated_at
            FROM stock s
            JOIN products p ON s.product_id = p.product_id
            JOIN warehouses w ON s.warehouse_id = w.warehouse_id
            WHERE s.product_id = p_product_id
            ORDER BY w.warehouse_name;
    END get_stock_by_product;


    PROCEDURE get_all_stock (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT s.stock_id,
                   s.product_id,
                   p.product_code,
                   p.product_name,
                   s.warehouse_id,
                   w.warehouse_name,
                   s.quantity,
                   p.reorder_level,
                   CASE
                       WHEN s.quantity <= p.reorder_level THEN 'LOW_STOCK'
                       ELSE 'AVAILABLE'
                   END AS stock_status,
                   s.created_at,
                   s.updated_at
            FROM stock s
            JOIN products p ON s.product_id = p.product_id
            JOIN warehouses w ON s.warehouse_id = w.warehouse_id
            ORDER BY p.product_name, w.warehouse_name;
    END get_all_stock;


    PROCEDURE get_stock_transactions (
        p_product_id IN stock_transactions.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT st.transaction_id,
                   st.product_id,
                   p.product_name,
                   st.warehouse_id,
                   w.warehouse_name,
                   st.transaction_type,
                   st.quantity,
                   st.transaction_date,
                   st.reference_no,
                   st.remarks,
                   st.created_at
            FROM stock_transactions st
            JOIN products p ON st.product_id = p.product_id
            JOIN warehouses w ON st.warehouse_id = w.warehouse_id
            WHERE st.product_id = p_product_id
            ORDER BY st.transaction_date DESC;
    END get_stock_transactions;

END inventory_pkg;
/
