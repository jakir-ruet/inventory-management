package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class StockRowMapper implements RowMapper<Stock> {

    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {

        Stock stock = new Stock();

        stock.setStockId(rs.getLong("STOCK_ID"));
        stock.setProductId(rs.getLong("PRODUCT_ID"));
        stock.setProductCode(rs.getString("PRODUCT_CODE"));
        stock.setProductName(rs.getString("PRODUCT_NAME"));
        stock.setWarehouseId(rs.getLong("WAREHOUSE_ID"));
        stock.setWarehouseName(rs.getString("WAREHOUSE_NAME"));
        stock.setQuantity(rs.getInt("QUANTITY"));
        stock.setReorderLevel(rs.getInt("REORDER_LEVEL"));
        stock.setStockStatus(rs.getString("STOCK_STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            stock.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            stock.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return stock;
    }
}

// Expected SQL in INVENTORY_PKG
// Your package should return something similar to:

// SELECT
//     s.stock_id,
//     s.product_id,
//     p.product_code,
//     p.product_name,
//     s.warehouse_id,
//     w.warehouse_name,
//     s.quantity,
//     p.reorder_level,
//     CASE
//         WHEN s.quantity <= p.reorder_level
//             THEN 'LOW_STOCK'
//         ELSE 'AVAILABLE'
//     END AS stock_status,
//     s.created_at,
//     s.updated_at
// FROM stock s
// JOIN products p
//     ON s.product_id = p.product_id
// JOIN warehouses w
//     ON s.warehouse_id = w.warehouse_id;

// This aligns perfectly with your Stock.java model.
