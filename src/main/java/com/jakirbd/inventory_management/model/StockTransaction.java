package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class StockTransaction {

	private Long transactionId;
	private Long productId;
	private String productName;
	private String productCode;
	private Long warehouseId;
	private String warehouseName;
	private String transactionType;
	private Integer quantity;
	private LocalDateTime transactionDate;
	private String referenceNo;
	private String remarks;
	private LocalDateTime createdAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public StockTransaction() {
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
			"StockTransaction{transactionId=%d, productId=%d, productName='%s', warehouseId=%d, warehouseName='%s', transactionType='%s', quantity=%d, transactionDate=%s, referenceNo='%s', remarks='%s', createdAt=%s}",
			transactionId,
			productId,
			productName,
			warehouseId,
			warehouseName,
			transactionType,
			quantity,
			transactionDate,
			referenceNo,
			remarks,
			createdAt
		);
	}
}

// productName and warehouseName are joined/display fields from INVENTORY_PKG
// GET_STOCK_TRANSACTIONS, not physical columns in stock_transactions.
