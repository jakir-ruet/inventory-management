package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.WarehouseCreateRequest;
import com.jakirbd.inventory_management.dto.WarehouseResponse;
import com.jakirbd.inventory_management.dto.WarehouseUpdateRequest;
import com.jakirbd.inventory_management.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createWarehouse(@Valid @RequestBody WarehouseCreateRequest request) {
        return warehouseService.createWarehouse(request);
    }

    @GetMapping("/{warehouseId}")
    public WarehouseResponse getWarehouseById(@PathVariable Long warehouseId) {
        return warehouseService.getWarehouseById(warehouseId);
    }

    @GetMapping
    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @PutMapping("/{warehouseId}")
    public void updateWarehouse(
            @PathVariable Long warehouseId,
            @Valid @RequestBody WarehouseUpdateRequest request) {

        warehouseService.updateWarehouse(warehouseId, request);
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deactivateWarehouse(warehouseId);
    }
}
