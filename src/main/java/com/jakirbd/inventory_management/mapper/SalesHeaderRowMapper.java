package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.SalesHeader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SalesHeaderRowMapper implements RowMapper<SalesHeader> {

    @Override
    public SalesHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
        SalesHeader salesHeader = new SalesHeader();

        salesHeader.setSalesId(rs.getLong("SALES_ID"));
        salesHeader.setBuyerId(rs.getLong("BUYER_ID"));
        salesHeader.setBuyerName(rs.getString("BUYER_NAME"));
        salesHeader.setWarehouseId(rs.getLong("WAREHOUSE_ID"));
        salesHeader.setWarehouseName(rs.getString("WAREHOUSE_NAME"));

        Timestamp salesDate = rs.getTimestamp("SALES_DATE");
        if (salesDate != null) {
            salesHeader.setSalesDate(salesDate.toLocalDateTime());
        }

        salesHeader.setInvoiceNo(rs.getString("INVOICE_NO"));
        salesHeader.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
        salesHeader.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            salesHeader.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            salesHeader.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return salesHeader;
    }
}
