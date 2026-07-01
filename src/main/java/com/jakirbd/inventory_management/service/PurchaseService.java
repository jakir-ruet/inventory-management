package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.PurchaseCreateRequest;
import com.jakirbd.inventory_management.dto.PurchaseLineRequest;
import com.jakirbd.inventory_management.dto.PurchaseLineResponse;
import com.jakirbd.inventory_management.dto.PurchaseResponse;
import com.jakirbd.inventory_management.model.PurchaseHeader;
import com.jakirbd.inventory_management.model.PurchaseLine;
import com.jakirbd.inventory_management.repository.PurchaseHeaderRepository;
import com.jakirbd.inventory_management.repository.PurchaseLineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PurchaseService {

    private final PurchaseHeaderRepository purchaseHeaderRepository;
    private final PurchaseLineRepository purchaseLineRepository;

    public PurchaseService(
            PurchaseHeaderRepository purchaseHeaderRepository,
            PurchaseLineRepository purchaseLineRepository) {

        this.purchaseHeaderRepository = purchaseHeaderRepository;
        this.purchaseLineRepository = purchaseLineRepository;
    }

    @Transactional
    public Long createPurchase(PurchaseCreateRequest request) {

        PurchaseHeader purchase = new PurchaseHeader();

        purchase.setSupplierId(request.getSupplierId());
        purchase.setWarehouseId(request.getWarehouseId());
        purchase.setInvoiceNo(request.getInvoiceNo());

        return purchaseHeaderRepository.create(purchase);
    }

    @Transactional
    public void addPurchaseLine(Long purchaseId,
                                PurchaseLineRequest request) {

        PurchaseLine line = new PurchaseLine();

        line.setPurchaseId(purchaseId);
        line.setProductId(request.getProductId());
        line.setQuantity(request.getQuantity());
        line.setUnitCost(request.getUnitCost());

        purchaseLineRepository.addLine(line);
    }

    @Transactional
    public void confirmPurchase(Long purchaseId) {
        purchaseHeaderRepository.confirm(purchaseId);
    }

    public PurchaseResponse getPurchaseById(Long purchaseId) {

        PurchaseHeader purchase = purchaseHeaderRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found."));

        return mapToResponse(purchase);
    }

    private PurchaseResponse mapToResponse(PurchaseHeader purchase) {

        PurchaseResponse response = new PurchaseResponse();

        response.setPurchaseId(purchase.getPurchaseId());
        response.setSupplierId(purchase.getSupplierId());
        response.setSupplierName(purchase.getSupplierName());

        response.setWarehouseId(purchase.getWarehouseId());
        response.setWarehouseName(purchase.getWarehouseName());

        response.setPurchaseDate(purchase.getPurchaseDate());

        response.setInvoiceNo(purchase.getInvoiceNo());
        response.setTotalAmount(purchase.getTotalAmount());
        response.setStatus(purchase.getStatus());

        response.setItems(new ArrayList<PurchaseLineResponse>());

        response.setCreatedAt(purchase.getCreatedAt());
        response.setUpdatedAt(purchase.getUpdatedAt());

        return response;
    }
}
