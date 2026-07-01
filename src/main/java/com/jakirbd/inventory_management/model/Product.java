package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

	public class Product {
	private Long productId;
	private Long categoryId;
	private String categoryName;
	private String productCode;
	private String productName;
	private Double unitPrice;
	private Integer reorderLevel;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public Product() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
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
					"Product{productId=%d, categoryId=%d, categoryName='%s', productCode='%s', productName='%s', unitPrice=%s, reorderLevel=%d, status='%s', createdAt=%s, updatedAt=%s}",
					productId,
					categoryId,
					categoryName,
					productCode,
					productName,
					unitPrice,
					reorderLevel,
					status,
					createdAt,
					updatedAt
		);
	}
}

// Note: categoryName is not a physical column in products;
// it comes from your package query join with categories.
