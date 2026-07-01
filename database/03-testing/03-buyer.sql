---------------------------------------------------
-- Add Buyer & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_buyer_id NUMBER;
BEGIN
    buyer_pkg.add_buyer(
        p_buyer_name     => 'XYZ Retail Ltd.',
        p_contact_person => 'Karim Ahmed',
        p_phone          => '01810000001',
        p_email          => 'xyz@example.com',
        p_address        => 'Dhaka, Bangladesh',
        p_buyer_id       => v_buyer_id
    );

    DBMS_OUTPUT.PUT_LINE('Created Buyer ID: ' || v_buyer_id);
END;
/

-----------------------------------
-- Check Buyer Inserted or Not
-----------------------------------
SELECT * FROM BUYERS;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'BUYER_PKG';

