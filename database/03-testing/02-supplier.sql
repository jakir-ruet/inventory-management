---------------------------------------------------
-- Add Supplier & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_supplier_id NUMBER;
BEGIN
    supplier_pkg.add_supplier(
        p_supplier_name  => 'ABC Trading Ltd.',
        p_contact_person => 'Rahim Uddin',
        p_phone          => '01710000001',
        p_email          => 'abc@example.com',
        p_address        => 'Dhaka, Bangladesh',
        p_supplier_id    => v_supplier_id
    );

    DBMS_OUTPUT.PUT_LINE('Created Supplier ID: ' || v_supplier_id);
END;
/

-----------------------------------
-- Check SUPPLIER Inserted or Not
-----------------------------------
SELECT * FROM suppliers;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'SUPPLIER_PKG';

