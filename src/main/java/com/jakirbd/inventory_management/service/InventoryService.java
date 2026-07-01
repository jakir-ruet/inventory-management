package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.StockInitializeRequest;
import com.jakirbd.inventory_management.dto.StockResponse;
import com.jakirbd.inventory_management.dto.StockTransactionResponse;
import com.jakirbd.inventory_management.model.Stock;
import com.jakirbd.inventory_management.model.StockTransaction;
import com.jakirbd.inventory_management.repository.StockRepository;
import com.jakirbd.inventory_management.repository.StockTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final StockRepository stockRepository;
    private final StockTransactionRepository stockTransactionRepository;

    public InventoryService(
            StockRepository stockRepository,
            StockTransactionRepository stockTransactionRepository
    ) {
        this.stockRepository = stockRepository;
        this.stockTransactionRepository = stockTransactionRepository;
    }

    @Transactional
    public void initializeStock(StockInitializeRequest request) {
        Stock stock = new Stock();

        stock.setProductId(request.getProductId());
        stock.setWarehouseId(request.getWarehouseId());
        stock.setQuantity(request.getQuantity());

        stockRepository.initializeStock(stock);
    }

    public List<StockResponse> getAllStock() {
        return stockRepository.findAll()
                .stream()
                .map(this::mapToStockResponse)
                .toList();
    }

    public List<StockResponse> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId)
                .stream()
                .map(this::mapToStockResponse)
                .toList();
    }

    public List<StockTransactionResponse> getStockTransactionsByProductId(Long productId) {
        return stockTransactionRepository.findByProductId(productId)
                .stream()
                .map(this::mapToStockTransactionResponse)
                .toList();
    }

    private StockResponse mapToStockResponse(Stock stock) {
        StockResponse response = new StockResponse();

        response.setStockId(stock.getStockId());
        response.setProductId(stock.getProductId());
        response.setProductCode(stock.getProductCode());
        response.setProductName(stock.getProductName());
        response.setWarehouseId(stock.getWarehouseId());
        response.setWarehouseName(stock.getWarehouseName());
        response.setQuantity(stock.getQuantity());
        response.setReorderLevel(stock.getReorderLevel());
        response.setStockStatus(stock.getStockStatus());
        response.setCreatedAt(stock.getCreatedAt());
        response.setUpdatedAt(stock.getUpdatedAt());

        return response;
    }

    private StockTransactionResponse mapToStockTransactionResponse(StockTransaction transaction) {
        StockTransactionResponse response = new StockTransactionResponse();

        response.setTransactionId(transaction.getTransactionId());
        response.setProductId(transaction.getProductId());
        response.setProductCode(transaction.getProductCode());
        response.setProductName(transaction.getProductName());
        response.setWarehouseId(transaction.getWarehouseId());
        response.setWarehouseName(transaction.getWarehouseName());
        response.setTransactionType(transaction.getTransactionType());
        response.setQuantity(transaction.getQuantity());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setReferenceNo(transaction.getReferenceNo());
        response.setRemarks(transaction.getRemarks());
        response.setCreatedAt(transaction.getCreatedAt());

        return response;
    }
}
