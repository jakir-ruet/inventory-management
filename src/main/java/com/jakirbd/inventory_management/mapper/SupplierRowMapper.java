package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Supplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SupplierRowMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {

        Supplier supplier = new Supplier();

        supplier.setSupplierId(rs.getLong("SUPPLIER_ID"));
        supplier.setSupplierName(rs.getString("SUPPLIER_NAME"));
        supplier.setContactPerson(rs.getString("CONTACT_PERSON"));
        supplier.setPhone(rs.getString("PHONE"));
        supplier.setEmail(rs.getString("EMAIL"));
        supplier.setAddress(rs.getString("ADDRESS"));
        supplier.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            supplier.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            supplier.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return supplier;
    }
}
