package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseLineRequest {

    @NotNull(message = "Product is required.")
    private Long productId;

    @NotNull(message = "Quantity is required.")
    @Min(value = 1)
    private Integer quantity;

    @NotNull(message = "Unit cost is required.")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double unitCost;

    public PurchaseLineRequest() {
    }

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

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
}
