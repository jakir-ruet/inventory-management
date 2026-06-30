package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class SalesLine {

	private Long salesLineId;
	private Long salesId;
	private Long productId;
	private String productCode;
	private String productName;
	private Integer quantity;
	private Double unitPrice;
	private Double lineTotal;
	private LocalDateTime createdAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public SalesLine() {
	}

	public Long getSalesLineId() {
		return salesLineId;
	}

	public void setSalesLineId(Long salesLineId) {
		this.salesLineId = salesLineId;
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
			"SalesLine{salesLineId=%d, salesId=%d, productId=%d, productCode='%s', productName='%s', quantity=%d, unitPrice=%s, lineTotal=%s, createdAt=%s}",
			salesLineId,
			salesId,
			productId,
			productCode,
			productName,
			quantity,
			unitPrice,
			lineTotal,
			createdAt
		);
	}
}
