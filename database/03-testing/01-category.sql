---------------------------------------------------
-- Add category & Test
---------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE
    v_category_id NUMBER;
BEGIN
    category_pkg.add_category(
        p_category_name => 'Electronics',
        p_description   => 'Electronic items',
        p_category_id   => v_category_id
    );

    DBMS_OUTPUT.PUT_LINE('Created Category ID: ' || v_category_id);
END;
/

-----------------------------------
-- Check Category Inserted or Not
-----------------------------------
SELECT * FROM categories;

-----------------------------------
-- Check Package Created or Not
-----------------------------------
SELECT object_name, object_type, status
FROM user_objects
WHERE object_name = 'CATEGORY_PKG';
