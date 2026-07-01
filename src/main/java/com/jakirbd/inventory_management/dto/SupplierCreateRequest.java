package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierCreateRequest {

    @NotBlank(message = "Supplier name is required.")
    @Size(max = 150, message = "Supplier name must not exceed 150 characters.")
    private String supplierName;

    @Size(max = 100, message = "Contact person must not exceed 100 characters.")
    private String contactPerson;

    @Size(max = 30, message = "Phone must not exceed 30 characters.")
    private String phone;

    @Email(message = "Invalid email format.")
    @Size(max = 150, message = "Email must not exceed 150 characters.")
    private String email;

    @Size(max = 500, message = "Address must not exceed 500 characters.")
    private String address;

    public SupplierCreateRequest() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    @Override
    public String toString() {
        return String.format(
                "SupplierCreateRequest{supplierName='%s', contactPerson='%s', phone='%s', email='%s', address='%s'}",
                supplierName,
                contactPerson,
                phone,
                email,
                address
        );
    }
}
