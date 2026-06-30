package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.SalesCreateRequest;
import com.jakirbd.inventory_management.dto.SalesLineRequest;
import com.jakirbd.inventory_management.dto.SalesResponse;
import com.jakirbd.inventory_management.service.SalesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createSales(@Valid @RequestBody SalesCreateRequest request) {
        return salesService.createSales(request);
    }

    @PostMapping("/{salesId}/lines")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSalesLine(
            @PathVariable Long salesId,
            @Valid @RequestBody SalesLineRequest request) {

        salesService.addSalesLine(salesId, request);
    }

    @PostMapping("/{salesId}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmSales(@PathVariable Long salesId) {
        salesService.confirmSales(salesId);
    }

    @GetMapping("/{salesId}")
    public SalesResponse getSalesById(@PathVariable Long salesId) {
        return salesService.getSalesById(salesId);
    }
}
