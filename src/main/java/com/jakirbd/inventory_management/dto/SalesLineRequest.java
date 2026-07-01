package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SalesLineRequest {

    @NotNull(message = "Product is required.")
    private Long productId;

    @NotNull(message = "Quantity is required.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    private Integer quantity;

    @NotNull(message = "Unit price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than zero.")
    private Double unitPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
