package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.PurchaseLine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PurchaseLineRowMapper implements RowMapper<PurchaseLine> {

    @Override
    public PurchaseLine mapRow(ResultSet rs, int rowNum) throws SQLException {
        PurchaseLine purchaseLine = new PurchaseLine();

        purchaseLine.setPurchaseLineId(rs.getLong("PURCHASE_LINE_ID"));
        purchaseLine.setPurchaseId(rs.getLong("PURCHASE_ID"));
        purchaseLine.setProductId(rs.getLong("PRODUCT_ID"));
        purchaseLine.setProductCode(rs.getString("PRODUCT_CODE"));
        purchaseLine.setProductName(rs.getString("PRODUCT_NAME"));
        purchaseLine.setQuantity(rs.getInt("QUANTITY"));
        purchaseLine.setUnitCost(rs.getDouble("UNIT_COST"));
        purchaseLine.setLineTotal(rs.getDouble("LINE_TOTAL"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            purchaseLine.setCreatedAt(createdAt.toLocalDateTime());
        }

        return purchaseLine;
    }
}
