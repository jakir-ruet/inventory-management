package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.StockInitializeRequest;
import com.jakirbd.inventory_management.dto.StockResponse;
import com.jakirbd.inventory_management.dto.StockTransactionResponse;
import com.jakirbd.inventory_management.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Initialize stock for a product in a warehouse.
     * Typically used only once when introducing a new product.
     */
    @PostMapping("/initialize")
    @ResponseStatus(HttpStatus.CREATED)
    public void initializeStock(
            @Valid @RequestBody StockInitializeRequest request) {

        inventoryService.initializeStock(request);
    }

    /**
     * Get current stock of all products.
     */
    @GetMapping("/stock")
    public List<StockResponse> getAllStock() {
        return inventoryService.getAllStock();
    }

    /**
     * Get stock by product.
     */
    @GetMapping("/stock/{productId}")
    public List<StockResponse> getStockByProductId(
            @PathVariable Long productId) {

        return inventoryService.getStockByProductId(productId);
    }

    /**
     * View inventory movement history.
     */
    @GetMapping("/transactions/{productId}")
    public List<StockTransactionResponse> getStockTransactions(
            @PathVariable Long productId) {

        return inventoryService.getStockTransactionsByProductId(productId);
    }
}
