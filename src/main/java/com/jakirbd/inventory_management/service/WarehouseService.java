package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.WarehouseCreateRequest;
import com.jakirbd.inventory_management.dto.WarehouseResponse;
import com.jakirbd.inventory_management.dto.WarehouseUpdateRequest;
import com.jakirbd.inventory_management.model.Warehouse;
import com.jakirbd.inventory_management.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public Long createWarehouse(WarehouseCreateRequest request) {
        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setLocation(request.getLocation());

        return warehouseRepository.create(warehouse);
    }

    @Transactional
    public void updateWarehouse(Long warehouseId, WarehouseUpdateRequest request) {
        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseId(warehouseId);
        warehouse.setWarehouseName(request.getWarehouseName());
        warehouse.setLocation(request.getLocation());
        warehouse.setStatus(request.getStatus());

        warehouseRepository.update(warehouse);
    }

    @Transactional
    public void deactivateWarehouse(Long warehouseId) {
        warehouseRepository.deactivate(warehouseId);
    }

    public WarehouseResponse getWarehouseById(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found."));

        return mapToResponse(warehouse);
    }

    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private WarehouseResponse mapToResponse(Warehouse warehouse) {
        WarehouseResponse response = new WarehouseResponse();

        response.setWarehouseId(warehouse.getWarehouseId());
        response.setWarehouseName(warehouse.getWarehouseName());
        response.setLocation(warehouse.getLocation());
        response.setStatus(warehouse.getStatus());
        response.setCreatedAt(warehouse.getCreatedAt());
        response.setUpdatedAt(warehouse.getUpdatedAt());

        return response;
    }
}
