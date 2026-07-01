package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.BuyerCreateRequest;
import com.jakirbd.inventory_management.dto.BuyerResponse;
import com.jakirbd.inventory_management.dto.BuyerUpdateRequest;
import com.jakirbd.inventory_management.service.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createBuyer(@Valid @RequestBody BuyerCreateRequest request) {
        return buyerService.createBuyer(request);
    }

    @GetMapping("/{buyerId}")
    public BuyerResponse getBuyerById(@PathVariable Long buyerId) {
        return buyerService.getBuyerById(buyerId);
    }

    @GetMapping
    public List<BuyerResponse> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @PutMapping("/{buyerId}")
    public void updateBuyer(
            @PathVariable Long buyerId,
            @Valid @RequestBody BuyerUpdateRequest request) {

        buyerService.updateBuyer(buyerId, request);
    }

    @DeleteMapping("/{buyerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateBuyer(@PathVariable Long buyerId) {
        buyerService.deactivateBuyer(buyerId);
    }
}
