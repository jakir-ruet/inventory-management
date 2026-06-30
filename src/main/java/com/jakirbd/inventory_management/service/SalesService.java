package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.SalesCreateRequest;
import com.jakirbd.inventory_management.dto.SalesLineRequest;
import com.jakirbd.inventory_management.dto.SalesLineResponse;
import com.jakirbd.inventory_management.dto.SalesResponse;
import com.jakirbd.inventory_management.model.SalesHeader;
import com.jakirbd.inventory_management.model.SalesLine;
import com.jakirbd.inventory_management.repository.SalesHeaderRepository;
import com.jakirbd.inventory_management.repository.SalesLineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class SalesService {

    private final SalesHeaderRepository salesHeaderRepository;
    private final SalesLineRepository salesLineRepository;

    public SalesService(
            SalesHeaderRepository salesHeaderRepository,
            SalesLineRepository salesLineRepository) {

        this.salesHeaderRepository = salesHeaderRepository;
        this.salesLineRepository = salesLineRepository;
    }

    @Transactional
    public Long createSales(SalesCreateRequest request) {
        SalesHeader sales = new SalesHeader();

        sales.setBuyerId(request.getBuyerId());
        sales.setWarehouseId(request.getWarehouseId());
        sales.setInvoiceNo(request.getInvoiceNo());

        return salesHeaderRepository.create(sales);
    }

    @Transactional
    public void addSalesLine(Long salesId, SalesLineRequest request) {
        SalesLine line = new SalesLine();

        line.setSalesId(salesId);
        line.setProductId(request.getProductId());
        line.setQuantity(request.getQuantity());
        line.setUnitPrice(request.getUnitPrice());

        salesLineRepository.addLine(line);
    }

    @Transactional
    public void confirmSales(Long salesId) {
        salesHeaderRepository.confirm(salesId);
    }

    public SalesResponse getSalesById(Long salesId) {
        SalesHeader sales = salesHeaderRepository.findById(salesId)
                .orElseThrow(() -> new RuntimeException("Sales not found."));

        return mapToResponse(sales);
    }

    private SalesResponse mapToResponse(SalesHeader sales) {
        SalesResponse response = new SalesResponse();

        response.setSalesId(sales.getSalesId());
        response.setBuyerId(sales.getBuyerId());
        response.setBuyerName(sales.getBuyerName());

        response.setWarehouseId(sales.getWarehouseId());
        response.setWarehouseName(sales.getWarehouseName());

        response.setSalesDate(sales.getSalesDate());
        response.setInvoiceNo(sales.getInvoiceNo());
        response.setTotalAmount(sales.getTotalAmount());
        response.setStatus(sales.getStatus());

        response.setItems(new ArrayList<SalesLineResponse>());

        response.setCreatedAt(sales.getCreatedAt());
        response.setUpdatedAt(sales.getUpdatedAt());

        return response;
    }
}
