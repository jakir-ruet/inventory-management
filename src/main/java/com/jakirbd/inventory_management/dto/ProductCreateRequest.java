package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateRequest {

    @NotNull(message = "Category is required.")
    private Long categoryId;

    @NotBlank(message = "Product code is required.")
    @Size(max = 50, message = "Product code must not exceed 50 characters.")
    private String productCode;

    @NotBlank(message = "Product name is required.")
    @Size(max = 150, message = "Product name must not exceed 150 characters.")
    private String productName;

    @NotNull(message = "Unit price is required.")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double unitPrice;

    @NotNull(message = "Reorder level is required.")
    @Min(value = 0)
    private Integer reorderLevel;

    public ProductCreateRequest() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}
