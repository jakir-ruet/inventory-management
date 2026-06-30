----------------------------------------------------------
-- Create PACKAGE Specification category_pkg
----------------------------------------------------------

CREATE OR REPLACE PACKAGE category_pkg AS

    PROCEDURE add_category (
        p_category_name IN categories.category_name%TYPE,
        p_description   IN categories.description%TYPE,
        p_category_id   OUT categories.category_id%TYPE
    );

    PROCEDURE update_category (
        p_category_id   IN categories.category_id%TYPE,
        p_category_name IN categories.category_name%TYPE,
        p_description   IN categories.description%TYPE,
        p_status        IN categories.status%TYPE
    );

    PROCEDURE deactivate_category (
        p_category_id IN categories.category_id%TYPE
    );

    PROCEDURE get_category_by_id (
        p_category_id IN categories.category_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_categories (
        p_result OUT SYS_REFCURSOR
    );

END category_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY category_pkg
----------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY category_pkg AS

    PROCEDURE add_category (
        p_category_name IN categories.category_name%TYPE,
        p_description   IN categories.description%TYPE,
        p_category_id   OUT categories.category_id%TYPE
    ) AS
    BEGIN
        INSERT INTO categories (
            category_name,
            description
        ) VALUES (
            p_category_name,
            p_description
        )
        RETURNING category_id INTO p_category_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20001, 'Category already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20099, 'Error adding category: ' || SQLERRM);
    END add_category;


    PROCEDURE update_category (
        p_category_id   IN categories.category_id%TYPE,
        p_category_name IN categories.category_name%TYPE,
        p_description   IN categories.description%TYPE,
        p_status        IN categories.status%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM categories
        WHERE category_id = p_category_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Category not found.');
        END IF;

        UPDATE categories
        SET category_name = p_category_name,
            description   = p_description,
            status        = p_status,
            updated_at    = SYSTIMESTAMP
        WHERE category_id = p_category_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20099, 'Error updating category: ' || SQLERRM);
    END update_category;


    PROCEDURE deactivate_category (
        p_category_id IN categories.category_id%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM categories
        WHERE category_id = p_category_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Category not found.');
        END IF;

        UPDATE categories
        SET status     = 'INACTIVE',
            updated_at = SYSTIMESTAMP
        WHERE category_id = p_category_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20099, 'Error deactivating category: ' || SQLERRM);
    END deactivate_category;


    PROCEDURE get_category_by_id (
        p_category_id IN categories.category_id%TYPE,
        p_result      OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT category_id,
                   category_name,
                   description,
                   status,
                   created_at,
                   updated_at
            FROM categories
            WHERE category_id = p_category_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20099, 'Error fetching category: ' || SQLERRM);
    END get_category_by_id;


    PROCEDURE get_all_categories (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT category_id,
                   category_name,
                   description,
                   status,
                   created_at,
                   updated_at
            FROM categories
            ORDER BY category_id DESC;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20099, 'Error fetching categories: ' || SQLERRM);
    END get_all_categories;

END category_pkg;
/
