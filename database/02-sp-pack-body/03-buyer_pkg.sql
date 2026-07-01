----------------------------------------------------------
-- Create PACKAGE Specification buyer_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE buyer_pkg AS

    PROCEDURE add_buyer (
        p_buyer_name     IN buyers.buyer_name%TYPE,
        p_contact_person IN buyers.contact_person%TYPE,
        p_phone          IN buyers.phone%TYPE,
        p_email          IN buyers.email%TYPE,
        p_address        IN buyers.address%TYPE,
        p_buyer_id       OUT buyers.buyer_id%TYPE
    );

    PROCEDURE update_buyer (
        p_buyer_id       IN buyers.buyer_id%TYPE,
        p_buyer_name     IN buyers.buyer_name%TYPE,
        p_contact_person IN buyers.contact_person%TYPE,
        p_phone          IN buyers.phone%TYPE,
        p_email          IN buyers.email%TYPE,
        p_address        IN buyers.address%TYPE,
        p_status         IN buyers.status%TYPE
    );

    PROCEDURE deactivate_buyer (
        p_buyer_id IN buyers.buyer_id%TYPE
    );

    PROCEDURE get_buyer_by_id (
        p_buyer_id IN buyers.buyer_id%TYPE,
        p_result   OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_buyers (
        p_result OUT SYS_REFCURSOR
    );

END buyer_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY buyer_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY buyer_pkg AS

    PROCEDURE add_buyer (
        p_buyer_name     IN buyers.buyer_name%TYPE,
        p_contact_person IN buyers.contact_person%TYPE,
        p_phone          IN buyers.phone%TYPE,
        p_email          IN buyers.email%TYPE,
        p_address        IN buyers.address%TYPE,
        p_buyer_id       OUT buyers.buyer_id%TYPE
    ) AS
    BEGIN
        INSERT INTO buyers (
            buyer_name,
            contact_person,
            phone,
            email,
            address
        ) VALUES (
            p_buyer_name,
            p_contact_person,
            p_phone,
            p_email,
            p_address
        )
        RETURNING buyer_id INTO p_buyer_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20201, 'Buyer phone or email already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20299, 'Error adding buyer: ' || SQLERRM);
    END add_buyer;


    PROCEDURE update_buyer (
        p_buyer_id       IN buyers.buyer_id%TYPE,
        p_buyer_name     IN buyers.buyer_name%TYPE,
        p_contact_person IN buyers.contact_person%TYPE,
        p_phone          IN buyers.phone%TYPE,
        p_email          IN buyers.email%TYPE,
        p_address        IN buyers.address%TYPE,
        p_status         IN buyers.status%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM buyers
        WHERE buyer_id = p_buyer_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20202, 'Buyer not found.');
        END IF;

        UPDATE buyers
        SET buyer_name      = p_buyer_name,
            contact_person  = p_contact_person,
            phone           = p_phone,
            email           = p_email,
            address         = p_address,
            status          = p_status,
            updated_at      = SYSTIMESTAMP
        WHERE buyer_id = p_buyer_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20201, 'Buyer phone or email already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20299, 'Error updating buyer: ' || SQLERRM);
    END update_buyer;


    PROCEDURE deactivate_buyer (
        p_buyer_id IN buyers.buyer_id%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM buyers
        WHERE buyer_id = p_buyer_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20202, 'Buyer not found.');
        END IF;

        UPDATE buyers
        SET status     = 'INACTIVE',
            updated_at = SYSTIMESTAMP
        WHERE buyer_id = p_buyer_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20299, 'Error deactivating buyer: ' || SQLERRM);
    END deactivate_buyer;


    PROCEDURE get_buyer_by_id (
        p_buyer_id IN buyers.buyer_id%TYPE,
        p_result   OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT buyer_id,
                   buyer_name,
                   contact_person,
                   phone,
                   email,
                   address,
                   status,
                   created_at,
                   updated_at
            FROM buyers
            WHERE buyer_id = p_buyer_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20299, 'Error fetching buyer: ' || SQLERRM);
    END get_buyer_by_id;


    PROCEDURE get_all_buyers (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT buyer_id,
                   buyer_name,
                   contact_person,
                   phone,
                   email,
                   address,
                   status,
                   created_at,
                   updated_at
            FROM buyers
            ORDER BY buyer_id DESC;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20299, 'Error fetching buyers: ' || SQLERRM);
    END get_all_buyers;

END buyer_pkg;
/
