package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.StockTransaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class StockTransactionRowMapper implements RowMapper<StockTransaction> {

    @Override
    public StockTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        StockTransaction stockTransaction = new StockTransaction();

        stockTransaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
        stockTransaction.setProductId(rs.getLong("PRODUCT_ID"));
        stockTransaction.setProductCode(rs.getString("PRODUCT_CODE"));
        stockTransaction.setProductName(rs.getString("PRODUCT_NAME"));
        stockTransaction.setWarehouseId(rs.getLong("WAREHOUSE_ID"));
        stockTransaction.setWarehouseName(rs.getString("WAREHOUSE_NAME"));
        stockTransaction.setTransactionType(rs.getString("TRANSACTION_TYPE"));
        stockTransaction.setQuantity(rs.getInt("QUANTITY"));

        Timestamp transactionDate = rs.getTimestamp("TRANSACTION_DATE");
        if (transactionDate != null) {
            stockTransaction.setTransactionDate(transactionDate.toLocalDateTime());
        }

        stockTransaction.setReferenceNo(rs.getString("REFERENCE_NO"));
        stockTransaction.setRemarks(rs.getString("REMARKS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            stockTransaction.setCreatedAt(createdAt.toLocalDateTime());
        }

        return stockTransaction;
    }
}
