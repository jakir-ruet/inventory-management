package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BuyerUpdateRequest {

    @NotBlank(message = "Buyer name is required.")
    @Size(max = 150, message = "Buyer name must not exceed 150 characters.")
    private String buyerName;

    @Size(max = 100, message = "Contact person must not exceed 100 characters.")
    private String contactPerson;

    @Size(max = 30, message = "Phone must not exceed 30 characters.")
    private String phone;

    @Email(message = "Invalid email format.")
    @Size(max = 150, message = "Email must not exceed 150 characters.")
    private String email;

    @Size(max = 500, message = "Address must not exceed 500 characters.")
    private String address;

    @NotBlank(message = "Status is required.")
    @Pattern(
            regexp = "ACTIVE|INACTIVE",
            message = "Status must be either ACTIVE or INACTIVE."
    )
    private String status;

    public BuyerUpdateRequest() {
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

    @Override
    public String toString() {
        return String.format(
                "BuyerUpdateRequest{buyerName='%s', contactPerson='%s', phone='%s', email='%s', address='%s', status='%s'}",
                buyerName,
                contactPerson,
                phone,
                email,
                address,
                status
        );
    }
}
