package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class ProductSupplier {

	private Long productSupplierId;
	private Long productId;
	private String productName;
	private Long supplierId;
	private String supplierName;
	private Double supplierPrice;
	private Integer leadTimeDays;
	private String isPrimarySupplier;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public ProductSupplier() {
	}

	public Long getProductSupplierId() {
		return productSupplierId;
	}

	public void setProductSupplierId(Long productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Integer getLeadTimeDays() {
		return leadTimeDays;
	}

	public void setLeadTimeDays(Integer leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}

	public String getIsPrimarySupplier() {
		return isPrimarySupplier;
	}

	public void setIsPrimarySupplier(String isPrimarySupplier) {
		this.isPrimarySupplier = isPrimarySupplier;
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
				"ProductSupplier{productSupplierId=%d, productId=%d, productName='%s', supplierId=%d, supplierName='%s', supplierPrice=%s, leadTimeDays=%d, isPrimarySupplier='%s', status='%s', createdAt=%s, updatedAt=%s}",
				productSupplierId,
				productId,
				productName,
				supplierId,
				supplierName,
				supplierPrice,
				leadTimeDays,
				isPrimarySupplier,
				status,
				createdAt,
				updatedAt);
	}
}

// productName and supplierName are not physical columns in product_suppliers; they come from joins in your PRODUCT_PKG.GET_PRODUCT_SUPPLIERS.
