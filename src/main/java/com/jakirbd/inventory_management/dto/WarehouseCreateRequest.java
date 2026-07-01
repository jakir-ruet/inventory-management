package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WarehouseCreateRequest {

    @NotBlank(message = "Warehouse name is required.")
    @Size(max = 150, message = "Warehouse name must not exceed 150 characters.")
    private String warehouseName;

    @Size(max = 300, message = "Location must not exceed 300 characters.")
    private String location;

    public WarehouseCreateRequest() {
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
