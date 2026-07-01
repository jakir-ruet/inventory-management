package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductSupplierCreateRequest {

    @NotNull(message = "Product is required.")
    private Long productId;

    @NotNull(message = "Supplier is required.")
    private Long supplierId;

    @NotNull(message = "Supplier price is required.")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double supplierPrice;

    @NotNull(message = "Lead time is required.")
    @Min(value = 0)
    private Integer leadTimeDays;

    @NotBlank(message = "Primary supplier flag is required.")
    private String isPrimarySupplier;

    public ProductSupplierCreateRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
}
