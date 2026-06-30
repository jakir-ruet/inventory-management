package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.SupplierCreateRequest;
import com.jakirbd.inventory_management.dto.SupplierResponse;
import com.jakirbd.inventory_management.dto.SupplierUpdateRequest;
import com.jakirbd.inventory_management.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createSupplier(@Valid @RequestBody SupplierCreateRequest request) {
        return supplierService.createSupplier(request);
    }

    @GetMapping("/{supplierId}")
    public SupplierResponse getSupplierById(@PathVariable Long supplierId) {
        return supplierService.getSupplierById(supplierId);
    }

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PutMapping("/{supplierId}")
    public void updateSupplier(
            @PathVariable Long supplierId,
            @Valid @RequestBody SupplierUpdateRequest request) {
        supplierService.updateSupplier(supplierId, request);
    }

    @DeleteMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateSupplier(@PathVariable Long supplierId) {
        supplierService.deactivateSupplier(supplierId);
    }
}
