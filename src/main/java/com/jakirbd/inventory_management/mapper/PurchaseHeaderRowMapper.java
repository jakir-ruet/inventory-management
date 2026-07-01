package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.PurchaseHeader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PurchaseHeaderRowMapper implements RowMapper<PurchaseHeader> {

    @Override
    public PurchaseHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
        PurchaseHeader purchaseHeader = new PurchaseHeader();

        purchaseHeader.setPurchaseId(rs.getLong("PURCHASE_ID"));
        purchaseHeader.setSupplierId(rs.getLong("SUPPLIER_ID"));
        purchaseHeader.setSupplierName(rs.getString("SUPPLIER_NAME"));
        purchaseHeader.setWarehouseId(rs.getLong("WAREHOUSE_ID"));
        purchaseHeader.setWarehouseName(rs.getString("WAREHOUSE_NAME"));

        Timestamp purchaseDate = rs.getTimestamp("PURCHASE_DATE");
        if (purchaseDate != null) {
            purchaseHeader.setPurchaseDate(purchaseDate.toLocalDateTime());
        }

        purchaseHeader.setInvoiceNo(rs.getString("INVOICE_NO"));
        purchaseHeader.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
        purchaseHeader.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            purchaseHeader.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            purchaseHeader.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return purchaseHeader;
    }
}
