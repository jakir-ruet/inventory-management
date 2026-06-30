package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setCategoryId(rs.getLong("CATEGORY_ID"));
        category.setCategoryName(rs.getString("CATEGORY_NAME"));
        category.setDescription(rs.getString("DESCRIPTION"));
        category.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            category.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            category.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return category;
    }
}
