package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SalesCreateRequest {

    @NotNull(message = "Buyer is required.")
    private Long buyerId;

    @NotNull(message = "Warehouse is required.")
    private Long warehouseId;

    @NotBlank(message = "Invoice number is required.")
    private String invoiceNo;

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
