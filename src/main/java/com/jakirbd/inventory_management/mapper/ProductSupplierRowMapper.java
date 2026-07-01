package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.ProductSupplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductSupplierRowMapper implements RowMapper<ProductSupplier> {

    @Override
    public ProductSupplier mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductSupplier productSupplier = new ProductSupplier();

        productSupplier.setProductSupplierId(rs.getLong("PRODUCT_SUPPLIER_ID"));
        productSupplier.setProductId(rs.getLong("PRODUCT_ID"));
        productSupplier.setProductName(rs.getString("PRODUCT_NAME"));
        productSupplier.setSupplierId(rs.getLong("SUPPLIER_ID"));
        productSupplier.setSupplierName(rs.getString("SUPPLIER_NAME"));
        productSupplier.setSupplierPrice(rs.getDouble("SUPPLIER_PRICE"));
        productSupplier.setLeadTimeDays(rs.getInt("LEAD_TIME_DAYS"));
        productSupplier.setIsPrimarySupplier(rs.getString("IS_PRIMARY_SUPPLIER"));
        productSupplier.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            productSupplier.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            productSupplier.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return productSupplier;
    }
}

// Expected Query from PRODUCT_PKG
// Your package should return the joined information

// SELECT
//     ps.product_supplier_id,
//     ps.product_id,
//     p.product_name,
//     ps.supplier_id,
//     s.supplier_name,
//     ps.supplier_price,
//     ps.lead_time_days,
//     ps.is_primary_supplier,
//     ps.status,
//     ps.created_at,
//     ps.updated_at
// FROM product_suppliers ps
// JOIN products p
//     ON ps.product_id = p.product_id
// JOIN suppliers s
//     ON ps.supplier_id = s.supplier_id;
