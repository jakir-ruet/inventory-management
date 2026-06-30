package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StockInitializeRequest {

    @NotNull(message = "Product is required.")
    private Long productId;

    @NotNull(message = "Warehouse is required.")
    private Long warehouseId;

    @NotNull(message = "Quantity is required.")
    @Min(value = 0, message = "Quantity must be zero or greater.")
    private Integer quantity;

    public StockInitializeRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
