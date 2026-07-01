package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.SupplierCreateRequest;
import com.jakirbd.inventory_management.dto.SupplierResponse;
import com.jakirbd.inventory_management.dto.SupplierUpdateRequest;
import com.jakirbd.inventory_management.model.Supplier;
import com.jakirbd.inventory_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Long createSupplier(SupplierCreateRequest request) {
        Supplier supplier = new Supplier();

        supplier.setSupplierName(request.getSupplierName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());

        return supplierRepository.create(supplier);
    }

    @Transactional
    public void updateSupplier(Long supplierId, SupplierUpdateRequest request) {
        Supplier supplier = new Supplier();

        supplier.setSupplierId(supplierId);
        supplier.setSupplierName(request.getSupplierName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setStatus(request.getStatus());

        supplierRepository.update(supplier);
    }

    @Transactional
    public void deactivateSupplier(Long supplierId) {
        supplierRepository.deactivate(supplierId);
    }

    public SupplierResponse getSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found."));

        return mapToResponse(supplier);
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();

        response.setSupplierId(supplier.getSupplierId());
        response.setSupplierName(supplier.getSupplierName());
        response.setContactPerson(supplier.getContactPerson());
        response.setPhone(supplier.getPhone());
        response.setEmail(supplier.getEmail());
        response.setAddress(supplier.getAddress());
        response.setStatus(supplier.getStatus());
        response.setCreatedAt(supplier.getCreatedAt());
        response.setUpdatedAt(supplier.getUpdatedAt());

        return response;
    }
}
