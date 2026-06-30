package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.BuyerCreateRequest;
import com.jakirbd.inventory_management.dto.BuyerResponse;
import com.jakirbd.inventory_management.dto.BuyerUpdateRequest;
import com.jakirbd.inventory_management.model.Buyer;
import com.jakirbd.inventory_management.repository.BuyerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Transactional
    public Long createBuyer(BuyerCreateRequest request) {
        Buyer buyer = new Buyer();

        buyer.setBuyerName(request.getBuyerName());
        buyer.setContactPerson(request.getContactPerson());
        buyer.setPhone(request.getPhone());
        buyer.setEmail(request.getEmail());
        buyer.setAddress(request.getAddress());

        return buyerRepository.create(buyer);
    }

    @Transactional
    public void updateBuyer(Long buyerId, BuyerUpdateRequest request) {
        Buyer buyer = new Buyer();

        buyer.setBuyerId(buyerId);
        buyer.setBuyerName(request.getBuyerName());
        buyer.setContactPerson(request.getContactPerson());
        buyer.setPhone(request.getPhone());
        buyer.setEmail(request.getEmail());
        buyer.setAddress(request.getAddress());
        buyer.setStatus(request.getStatus());

        buyerRepository.update(buyer);
    }

    @Transactional
    public void deactivateBuyer(Long buyerId) {
        buyerRepository.deactivate(buyerId);
    }

    public BuyerResponse getBuyerById(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found."));

        return mapToResponse(buyer);
    }

    public List<BuyerResponse> getAllBuyers() {
        return buyerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private BuyerResponse mapToResponse(Buyer buyer) {
        BuyerResponse response = new BuyerResponse();

        response.setBuyerId(buyer.getBuyerId());
        response.setBuyerName(buyer.getBuyerName());
        response.setContactPerson(buyer.getContactPerson());
        response.setPhone(buyer.getPhone());
        response.setEmail(buyer.getEmail());
        response.setAddress(buyer.getAddress());
        response.setStatus(buyer.getStatus());
        response.setCreatedAt(buyer.getCreatedAt());
        response.setUpdatedAt(buyer.getUpdatedAt());

        return response;
    }
}
