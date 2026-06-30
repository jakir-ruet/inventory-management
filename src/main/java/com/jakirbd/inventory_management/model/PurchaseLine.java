package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class PurchaseLine {

	private Long purchaseLineId;
	private Long purchaseId;
	private Long productId;
	private String productCode;
	private String productName;
	private Integer quantity;
	private Double unitCost;
	private Double lineTotal;
	private LocalDateTime createdAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public PurchaseLine() {
	}

	public Long getPurchaseLineId() {
		return purchaseLineId;
	}

	public void setPurchaseLineId(Long purchaseLineId) {
		this.purchaseLineId = purchaseLineId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(Double lineTotal) {
		this.lineTotal = lineTotal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return String.format(
			"PurchaseLine{purchaseLineId=%d, purchaseId=%d, productId=%d, productCode='%s', productName='%s', quantity=%d, unitCost=%s, lineTotal=%s, createdAt=%s}",
			purchaseLineId,
			purchaseId,
			productId,
			productCode,
			productName,
			quantity,
			unitCost,
			lineTotal,
			createdAt
		);
	}
}

// productCode and productName are joined/display fields from the products table, not physical columns in purchase_lines.
