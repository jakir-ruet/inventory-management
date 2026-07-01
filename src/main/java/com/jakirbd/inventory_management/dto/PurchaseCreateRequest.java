package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PurchaseCreateRequest {

    @NotNull(message = "Supplier is required.")
    private Long supplierId;

    @NotNull(message = "Warehouse is required.")
    private Long warehouseId;

    @NotBlank(message = "Invoice number is required.")
    private String invoiceNo;

    public PurchaseCreateRequest() {
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
