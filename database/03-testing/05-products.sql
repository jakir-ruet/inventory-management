---------------------------------------------------
-- Add Product & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_product_id NUMBER;
BEGIN
    product_pkg.add_product(
        p_category_id   => 1,
        p_product_code  => 'LAP-001',
        p_product_name  => 'Dell Laptop',
        p_unit_price    => 85000,
        p_reorder_level => 10,
        p_product_id    => v_product_id
    );

    DBMS_OUTPUT.PUT_LINE('Created Product ID: ' || v_product_id);
END;
/

-----------------------------------
-- Check Product Inserted or Not
-----------------------------------
SELECT * FROM PRODUCTS;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'PRODUCT_PKG';
