---------------------------------------------------
-- Add SAles & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_sales_id NUMBER;
BEGIN
    sales_pkg.create_sales(
        p_buyer_id     => 1,
        p_warehouse_id => 1,
        p_invoice_no   => 'SINV-001',
        p_sales_id     => v_sales_id
    );

    sales_pkg.add_sales_line(
        p_sales_id   => v_sales_id,
        p_product_id => 1,
        p_quantity   => 2,
        p_unit_price => 85000
    );

    sales_pkg.confirm_sales(
        p_sales_id => v_sales_id
    );

    DBMS_OUTPUT.PUT_LINE('Sales confirmed. Sales ID: ' || v_sales_id);
END;
/

-----------------------------------
-- Check Sales Inserted or Not
-----------------------------------
SELECT * FROM SALES_HEADERS;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'SALES_PKG';
