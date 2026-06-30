package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product product = new Product();

        product.setProductId(rs.getLong("PRODUCT_ID"));
        product.setCategoryId(rs.getLong("CATEGORY_ID"));
        product.setCategoryName(rs.getString("CATEGORY_NAME"));
        product.setProductCode(rs.getString("PRODUCT_CODE"));
        product.setProductName(rs.getString("PRODUCT_NAME"));
        product.setUnitPrice(rs.getDouble("UNIT_PRICE"));
        product.setReorderLevel(rs.getInt("REORDER_LEVEL"));
        product.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            product.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            product.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return product;
    }
}

// Expected SQL from PRODUCT_PKG
// Since your Product model contains categoryName, your package should return it.

// SELECT
//     p.product_id,
//     p.category_id,
//     c.category_name,
//     p.product_code,
//     p.product_name,
//     p.unit_price,
//     p.reorder_level,
//     p.status,
//     p.created_at,
//     p.updated_at
// FROM products p
// JOIN categories c
//     ON p.category_id = c.category_id;
