package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.PurchaseCreateRequest;
import com.jakirbd.inventory_management.dto.PurchaseLineRequest;
import com.jakirbd.inventory_management.dto.PurchaseResponse;
import com.jakirbd.inventory_management.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPurchase(@Valid @RequestBody PurchaseCreateRequest request) {
        return purchaseService.createPurchase(request);
    }

    @PostMapping("/{purchaseId}/lines")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPurchaseLine(
            @PathVariable Long purchaseId,
            @Valid @RequestBody PurchaseLineRequest request) {

        purchaseService.addPurchaseLine(purchaseId, request);
    }

    @PostMapping("/{purchaseId}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmPurchase(@PathVariable Long purchaseId) {
        purchaseService.confirmPurchase(purchaseId);
    }

    @GetMapping("/{purchaseId}")
    public PurchaseResponse getPurchaseById(@PathVariable Long purchaseId) {
        return purchaseService.getPurchaseById(purchaseId);
    }
}
