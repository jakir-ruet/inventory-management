---------------------------------------------------
-- Add Product & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_purchase_id NUMBER;
BEGIN
    purchase_pkg.create_purchase(
        p_supplier_id  => 1,
        p_warehouse_id => 1,
        p_invoice_no   => 'INV-001',
        p_purchase_id  => v_purchase_id
    );

    purchase_pkg.add_purchase_line(
        p_purchase_id => v_purchase_id,
        p_product_id  => 1,
        p_quantity    => 10,
        p_unit_cost   => 78000
    );

    purchase_pkg.confirm_purchase(
        p_purchase_id => v_purchase_id
    );

    DBMS_OUTPUT.PUT_LINE('Purchase confirmed. Purchase ID: ' || v_purchase_id);
END;
/

-----------------------------------
-- Check Purchase Inserted or Not
-----------------------------------
SELECT * FROM PURCHASE_HEADERS;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'PURCHASE_PKG';
