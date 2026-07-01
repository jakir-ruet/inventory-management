package com.jakirbd.inventory_management.dto;

import java.time.LocalDateTime;

public class BuyerResponse {

    private Long buyerId;
    private String buyerName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BuyerResponse() {
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format(
                "BuyerResponse{buyerId=%d, buyerName='%s', contactPerson='%s', phone='%s', email='%s', address='%s', status='%s', createdAt=%s, updatedAt=%s}",
                buyerId,
                buyerName,
                contactPerson,
                phone,
                email,
                address,
                status,
                createdAt,
                updatedAt
        );
    }
}
