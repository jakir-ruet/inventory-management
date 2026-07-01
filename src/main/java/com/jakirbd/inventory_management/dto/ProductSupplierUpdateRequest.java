package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductSupplierUpdateRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long supplierId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double supplierPrice;

    @NotNull
    @Min(0)
    private Integer leadTimeDays;

    @NotBlank
    private String isPrimarySupplier;

    @NotBlank
    @Pattern(
            regexp = "ACTIVE|INACTIVE",
            message = "Status must be ACTIVE or INACTIVE."
    )
    private String status;

    public ProductSupplierUpdateRequest() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
