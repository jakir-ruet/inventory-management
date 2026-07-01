package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Warehouse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class WarehouseRowMapper implements RowMapper<Warehouse> {

    @Override
    public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseId(rs.getLong("WAREHOUSE_ID"));
        warehouse.setWarehouseName(rs.getString("WAREHOUSE_NAME"));
        warehouse.setLocation(rs.getString("LOCATION"));
        warehouse.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            warehouse.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            warehouse.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return warehouse;
    }
}
