package com.jakirbd.inventory_management.dto;

import java.time.LocalDateTime;

public class ProductSupplierResponse {

    private Long productSupplierId;

    private Long productId;
    private String productCode;
    private String productName;

    private Long supplierId;
    private String supplierName;

    private Double supplierPrice;
    private Integer leadTimeDays;
    private String isPrimarySupplier;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductSupplierResponse() {
    }

    public Long getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(Long productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Double supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    public void setLeadTimeDays(Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    public String getIsPrimarySupplier() {
        return isPrimarySupplier;
    }

    public void setIsPrimarySupplier(String isPrimarySupplier) {
        this.isPrimarySupplier = isPrimarySupplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
