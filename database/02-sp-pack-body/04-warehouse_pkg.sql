----------------------------------------------------------
-- Create PACKAGE Specification warehouse_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE warehouse_pkg AS

    PROCEDURE add_warehouse (
        p_warehouse_name IN warehouses.warehouse_name%TYPE,
        p_location       IN warehouses.location%TYPE,
        p_warehouse_id   OUT warehouses.warehouse_id%TYPE
    );

    PROCEDURE update_warehouse (
        p_warehouse_id   IN warehouses.warehouse_id%TYPE,
        p_warehouse_name IN warehouses.warehouse_name%TYPE,
        p_location       IN warehouses.location%TYPE,
        p_status         IN warehouses.status%TYPE
    );

    PROCEDURE deactivate_warehouse (
        p_warehouse_id IN warehouses.warehouse_id%TYPE
    );

    PROCEDURE get_warehouse_by_id (
        p_warehouse_id IN warehouses.warehouse_id%TYPE,
        p_result       OUT SYS_REFCURSOR
    );

    PROCEDURE get_all_warehouses (
        p_result OUT SYS_REFCURSOR
    );

END warehouse_pkg;
/

----------------------------------------------------------
-- Create PACKAGE BODY warehouses_pkg
----------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY warehouse_pkg AS

    PROCEDURE add_warehouse (
        p_warehouse_name IN warehouses.warehouse_name%TYPE,
        p_location       IN warehouses.location%TYPE,
        p_warehouse_id   OUT warehouses.warehouse_id%TYPE
    ) AS
    BEGIN
        INSERT INTO warehouses (
            warehouse_name,
            location
        ) VALUES (
            p_warehouse_name,
            p_location
        )
        RETURNING warehouse_id INTO p_warehouse_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20301, 'Warehouse name already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20399, 'Error adding warehouse: ' || SQLERRM);
    END add_warehouse;


    PROCEDURE update_warehouse (
        p_warehouse_id   IN warehouses.warehouse_id%TYPE,
        p_warehouse_name IN warehouses.warehouse_name%TYPE,
        p_location       IN warehouses.location%TYPE,
        p_status         IN warehouses.status%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM warehouses
        WHERE warehouse_id = p_warehouse_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20302, 'Warehouse not found.');
        END IF;

        UPDATE warehouses
        SET warehouse_name = p_warehouse_name,
            location       = p_location,
            status         = p_status,
            updated_at     = SYSTIMESTAMP
        WHERE warehouse_id = p_warehouse_id;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20301, 'Warehouse name already exists.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20399, 'Error updating warehouse: ' || SQLERRM);
    END update_warehouse;


    PROCEDURE deactivate_warehouse (
        p_warehouse_id IN warehouses.warehouse_id%TYPE
    ) AS
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM warehouses
        WHERE warehouse_id = p_warehouse_id;

        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20302, 'Warehouse not found.');
        END IF;

        UPDATE warehouses
        SET status     = 'INACTIVE',
            updated_at = SYSTIMESTAMP
        WHERE warehouse_id = p_warehouse_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20399, 'Error deactivating warehouse: ' || SQLERRM);
    END deactivate_warehouse;


    PROCEDURE get_warehouse_by_id (
        p_warehouse_id IN warehouses.warehouse_id%TYPE,
        p_result       OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT warehouse_id,
                   warehouse_name,
                   location,
                   status,
                   created_at,
                   updated_at
            FROM warehouses
            WHERE warehouse_id = p_warehouse_id;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20399, 'Error fetching warehouse: ' || SQLERRM);
    END get_warehouse_by_id;


    PROCEDURE get_all_warehouses (
        p_result OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN p_result FOR
            SELECT warehouse_id,
                   warehouse_name,
                   location,
                   status,
                   created_at,
                   updated_at
            FROM warehouses
            ORDER BY warehouse_id DESC;

    EXCEPTION
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20399, 'Error fetching warehouses: ' || SQLERRM);
    END get_all_warehouses;

END warehouse_pkg;
/

