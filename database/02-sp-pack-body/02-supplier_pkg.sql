----------------------------------------------------------
-- Create PACKAGE Specification supplier_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE supplier_pkg AS

    PROCEDURE add_supplier (
        p_supplier_name  IN suppliers.supplier_name%TYPE,
        p_contact_person IN suppliers.contact_person%TYPE,
        p_phone          IN suppliers.phone%TYPE,
        p_email          IN suppliers.email%TYPE,
        p_address        IN suppliers.address%TYPE,
        p_supplier_id    OUT suppliers.supplier_id%TYPE
    );

    PROCEDURE update_supplier (
        p_supplier_id    IN suppliers.supplier_id%TYPE,
        p_supplier_name  IN suppliers.supplier_name%TYPE,
        p_contact_person IN suppliers.contact_person%TYPE,
        p_phone          IN suppliers.phone%TYPE,
        p_email          IN suppliers.email%TYPE,
        p_address        IN suppliers.address%TYPE,
        p_status         IN suppliers.status%TYPE
    );

    PROCEDURE deactivate_supplier (
        p_supplier_id IN suppliers.supplier_id%TYPE
    );

    PROCEDURE get_supplier_by_id (
        p_supplier_id IN suppliers.supplier_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_suppliers (
        p_result OUT SYS_REFCURSOR
    );

END supplier_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY supplier_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY supplier_pkg AS

    PROCEDURE add_supplier (
        p_supplier_name  IN suppliers.supplier_name%TYPE,
        p_contact_person IN suppliers.contact_person%TYPE,
        p_phone          IN suppliers.phone%TYPE,
        p_email          IN suppliers.email%TYPE,
        p_address        IN suppliers.address%TYPE,
        p_supplier_id    OUT suppliers.supplier_id%TYPE
    ) AS
    BEGIN
        INSERT INTO suppliers (
            supplier_name,
            contact_person,
            phone,
            email,
            address
        ) VALUES (
            p_supplier_name,
            p_contact_person,
            p_phone,
            p_email,
            p_address
        )
        RETURNING supplier_id INTO p_supplier_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20101, 'Supplier phone or email already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20199, 'Error adding supplier: ' || SQLERRM);
    END add_supplier;


    PROCEDURE update_supplier (
        p_supplier_id    IN suppliers.supplier_id%TYPE,
        p_supplier_name  IN suppliers.supplier_name%TYPE,
        p_contact_person IN suppliers.contact_person%TYPE,
        p_phone          IN suppliers.phone%TYPE,
        p_email          IN suppliers.email%TYPE,
        p_address        IN suppliers.address%TYPE,
        p_status         IN suppliers.status%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM suppliers
        WHERE supplier_id = p_supplier_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20102, 'Supplier not found.');
        END IF;

        UPDATE suppliers
        SET supplier_name  = p_supplier_name,
            contact_person = p_contact_person,
            phone          = p_phone,
            email          = p_email,
            address        = p_address,
            status         = p_status,
            updated_at     = SYSTIMESTAMP
        WHERE supplier_id = p_supplier_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20101, 'Supplier phone or email already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20199, 'Error updating supplier: ' || SQLERRM);
    END update_supplier;


    PROCEDURE deactivate_supplier (
        p_supplier_id IN suppliers.supplier_id%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM suppliers
        WHERE supplier_id = p_supplier_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20102, 'Supplier not found.');
        END IF;

        UPDATE suppliers
        SET status     = 'INACTIVE',
            updated_at = SYSTIMESTAMP
        WHERE supplier_id = p_supplier_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20199, 'Error deactivating supplier: ' || SQLERRM);
    END deactivate_supplier;


    PROCEDURE get_supplier_by_id (
        p_supplier_id IN suppliers.supplier_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT supplier_id,
                   supplier_name,
                   contact_person,
                   phone,
                   email,
                   address,
                   status,
                   created_at,
                   updated_at
            FROM suppliers
            WHERE supplier_id = p_supplier_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20199, 'Error fetching supplier: ' || SQLERRM);
    END get_supplier_by_id;


    PROCEDURE get_all_suppliers (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT supplier_id,
                   supplier_name,
                   contact_person,
                   phone,
                   email,
                   address,
                   status,
                   created_at,
                   updated_at
            FROM suppliers
            ORDER BY supplier_id DESC;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20199, 'Error fetching suppliers: ' || SQLERRM);
    END get_all_suppliers;

END supplier_pkg;
/

