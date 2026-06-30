package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WarehouseUpdateRequest {

    @NotBlank(message = "Warehouse name is required.")
    @Size(max = 150, message = "Warehouse name must not exceed 150 characters.")
    private String warehouseName;

    @Size(max = 300, message = "Location must not exceed 300 characters.")
    private String location;

    @NotBlank(message = "Status is required.")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be either ACTIVE or INACTIVE.")
    private String status;

    public WarehouseUpdateRequest() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
