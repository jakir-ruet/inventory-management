---------------------------------------------------
-- Add Warehouse & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_warehouse_id NUMBER;
BEGIN
    warehouse_pkg.add_warehouse(
        p_warehouse_name => 'Dhaka Main Warehouse',
        p_location       => 'Dhaka, Bangladesh',
        p_warehouse_id   => v_warehouse_id
    );

    DBMS_OUTPUT.PUT_LINE('Created Warehouse ID: ' || v_warehouse_id);
END;
/

-----------------------------------
-- Check Warehouse Inserted or Not
-----------------------------------
SELECT * FROM WAREHOUSES;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'WAREHOUSE_PKG';
