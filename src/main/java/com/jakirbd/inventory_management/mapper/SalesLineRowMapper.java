package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.SalesLine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SalesLineRowMapper implements RowMapper<SalesLine> {

    @Override
    public SalesLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        SalesLine salesLine = new SalesLine();

        salesLine.setSalesLineId(rs.getLong("SALES_LINE_ID"));
        salesLine.setSalesId(rs.getLong("SALES_ID"));
        salesLine.setProductId(rs.getLong("PRODUCT_ID"));
        salesLine.setProductCode(rs.getString("PRODUCT_CODE"));
        salesLine.setProductName(rs.getString("PRODUCT_NAME"));
        salesLine.setQuantity(rs.getInt("QUANTITY"));
        salesLine.setUnitPrice(rs.getDouble("UNIT_PRICE"));
        salesLine.setLineTotal(rs.getDouble("LINE_TOTAL"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            salesLine.setCreatedAt(createdAt.toLocalDateTime());
        }

        return salesLine;
    }
}
