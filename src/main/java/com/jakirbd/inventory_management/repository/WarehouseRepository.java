package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.WarehouseRowMapper;
import com.jakirbd.inventory_management.model.Warehouse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class WarehouseRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "WAREHOUSE_PKG";

    public WarehouseRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(Warehouse warehouse) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_WAREHOUSE_NAME", warehouse.getWarehouseName());
        params.put("P_LOCATION", warehouse.getLocation());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ADD_WAREHOUSE")
                .execute(params);

        return ((Number) result.get("P_WAREHOUSE_ID")).longValue();
    }

    public void update(Warehouse warehouse) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_WAREHOUSE_ID", warehouse.getWarehouseId());
        params.put("P_WAREHOUSE_NAME", warehouse.getWarehouseName());
        params.put("P_LOCATION", warehouse.getLocation());
        params.put("P_STATUS", warehouse.getStatus());

        createCall(PACKAGE_NAME, "UPDATE_WAREHOUSE").execute(params);
    }

    public void deactivate(Long warehouseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_WAREHOUSE_ID", warehouseId);

        createCall(PACKAGE_NAME, "DEACTIVATE_WAREHOUSE").execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<Warehouse> findById(Long warehouseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_WAREHOUSE_ID", warehouseId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_WAREHOUSE_BY_ID")
                .returningResultSet("P_RESULT", new WarehouseRowMapper())
                .execute(params);

        List<Warehouse> warehouses = (List<Warehouse>) result.get("P_RESULT");
        return warehouses.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Warehouse> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_WAREHOUSES")
                .returningResultSet("P_RESULT", new WarehouseRowMapper())
                .execute();

        return (List<Warehouse>) result.get("P_RESULT");
    }
}
