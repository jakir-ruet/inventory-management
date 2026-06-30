----------------------------------------------------------
-- Create PACKAGE Specification product_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE product_pkg AS

    PROCEDURE add_product (
        p_category_id   IN products.category_id%TYPE,
        p_product_code  IN products.product_code%TYPE,
        p_product_name  IN products.product_name%TYPE,
        p_unit_price    IN products.unit_price%TYPE,
        p_reorder_level IN products.reorder_level%TYPE,
        p_product_id    OUT products.product_id%TYPE
    );

    PROCEDURE update_product (
        p_product_id    IN products.product_id%TYPE,
        p_category_id   IN products.category_id%TYPE,
        p_product_code  IN products.product_code%TYPE,
        p_product_name  IN products.product_name%TYPE,
        p_unit_price    IN products.unit_price%TYPE,
        p_reorder_level IN products.reorder_level%TYPE,
        p_status        IN products.status%TYPE
    );

    PROCEDURE deactivate_product (
        p_product_id IN products.product_id%TYPE
    );

    PROCEDURE get_product_by_id (
        p_product_id IN products.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_products (
        p_result OUT SYS_REFCURSOR
    );

    PROCEDURE assign_supplier (
        p_product_id          IN product_suppliers.product_id%TYPE,
        p_supplier_id         IN product_suppliers.supplier_id%TYPE,
        p_supplier_price      IN product_suppliers.supplier_price%TYPE,
        p_lead_time_days      IN product_suppliers.lead_time_days%TYPE,
        p_is_primary_supplier IN product_suppliers.is_primary_supplier%TYPE,
        p_product_supplier_id OUT product_suppliers.product_supplier_id%TYPE
    );

    PROCEDURE get_product_suppliers (
        p_product_id IN product_suppliers.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    );

END product_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY product_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY product_pkg AS

    PROCEDURE add_product (
        p_category_id   IN products.category_id%TYPE,
        p_product_code  IN products.product_code%TYPE,
        p_product_name  IN products.product_name%TYPE,
        p_unit_price    IN products.unit_price%TYPE,
        p_reorder_level IN products.reorder_level%TYPE,
        p_product_id    OUT products.product_id%TYPE
    ) AS
    BEGIN
        INSERT INTO products (
            category_id,
            product_code,
            product_name,
            unit_price,
            reorder_level
        ) VALUES (
            p_category_id,
            p_product_code,
            p_product_name,
            p_unit_price,
            p_reorder_level
        )
        RETURNING product_id INTO p_product_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20401, 'Product code already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error adding product: ' || SQLERRM);
    END add_product;


    PROCEDURE update_product (
        p_product_id    IN products.product_id%TYPE,
        p_category_id   IN products.category_id%TYPE,
        p_product_code  IN products.product_code%TYPE,
        p_product_name  IN products.product_name%TYPE,
        p_unit_price    IN products.unit_price%TYPE,
        p_reorder_level IN products.reorder_level%TYPE,
        p_status        IN products.status%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM products
        WHERE product_id = p_product_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20402, 'Product not found.');
        END IF;

        UPDATE products
        SET category_id    = p_category_id,
            product_code   = p_product_code,
            product_name   = p_product_name,
            unit_price     = p_unit_price,
            reorder_level  = p_reorder_level,
            status         = p_status,
            updated_at     = SYSTIMESTAMP
        WHERE product_id = p_product_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20401, 'Product code already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error updating product: ' || SQLERRM);
    END update_product;


    PROCEDURE deactivate_product (
        p_product_id IN products.product_id%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM products
        WHERE product_id = p_product_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20402, 'Product not found.');
        END IF;

        UPDATE products
        SET status     = 'INACTIVE',
            updated_at = SYSTIMESTAMP
        WHERE product_id = p_product_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error deactivating product: ' || SQLERRM);
    END deactivate_product;


    PROCEDURE get_product_by_id (
        p_product_id IN products.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT p.product_id,
                   p.category_id,
                   c.category_name,
                   p.product_code,
                   p.product_name,
                   p.unit_price,
                   p.reorder_level,
                   p.status,
                   p.created_at,
                   p.updated_at
            FROM products p
            JOIN categories c
                ON p.category_id = c.category_id
            WHERE p.product_id = p_product_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error fetching product: ' || SQLERRM);
    END get_product_by_id;


    PROCEDURE get_all_products (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT p.product_id,
                   p.category_id,
                   c.category_name,
                   p.product_code,
                   p.product_name,
                   p.unit_price,
                   p.reorder_level,
                   p.status,
                   p.created_at,
                   p.updated_at
            FROM products p
            JOIN categories c
                ON p.category_id = c.category_id
            ORDER BY p.product_id DESC;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error fetching products: ' || SQLERRM);
    END get_all_products;


    PROCEDURE assign_supplier (
        p_product_id          IN product_suppliers.product_id%TYPE,
        p_supplier_id         IN product_suppliers.supplier_id%TYPE,
        p_supplier_price      IN product_suppliers.supplier_price%TYPE,
        p_lead_time_days      IN product_suppliers.lead_time_days%TYPE,
        p_is_primary_supplier IN product_suppliers.is_primary_supplier%TYPE,
        p_product_supplier_id OUT product_suppliers.product_supplier_id%TYPE
    ) AS
    BEGIN
        INSERT INTO product_suppliers (
            product_id,
            supplier_id,
            supplier_price,
            lead_time_days,
            is_primary_supplier
        ) VALUES (
            p_product_id,
            p_supplier_id,
            p_supplier_price,
            p_lead_time_days,
            p_is_primary_supplier
        )
        RETURNING product_supplier_id INTO p_product_supplier_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20403, 'This supplier is already assigned to this product.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error assigning supplier to product: ' || SQLERRM);
    END assign_supplier;


    PROCEDURE get_product_suppliers (
        p_product_id IN product_suppliers.product_id%TYPE,
        p_result     OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT ps.product_supplier_id,
                   ps.product_id,
                   p.product_name,
                   ps.supplier_id,
                   s.supplier_name,
                   ps.supplier_price,
                   ps.lead_time_days,
                   ps.is_primary_supplier,
                   ps.status,
                   ps.created_at,
                   ps.updated_at
            FROM product_suppliers ps
            JOIN products p
                ON ps.product_id = p.product_id
            JOIN suppliers s
                ON ps.supplier_id = s.supplier_id
            WHERE ps.product_id = p_product_id
            ORDER BY ps.is_primary_supplier DESC, s.supplier_name;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20499, 'Error fetching product suppliers: ' || SQLERRM);
    END get_product_suppliers;

END product_pkg;
/
