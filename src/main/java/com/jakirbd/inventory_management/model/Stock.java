package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class Stock {

	private Long stockId;
	private Long productId;
	private String productCode;
	private String productName;
	private Long warehouseId;
	private String warehouseName;
	private Integer quantity;
	private Integer reorderLevel;
	private String stockStatus;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public Stock() {
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
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
			"Stock{stockId=%d, productId=%d, productCode='%s', productName='%s', warehouseId=%d, warehouseName='%s', quantity=%d, reorderLevel=%d, stockStatus='%s', createdAt=%s, updatedAt=%s}",
			stockId,
			productId,
			productCode,
			productName,
			warehouseId,
			warehouseName,
			quantity,
			reorderLevel,
			stockStatus,
			createdAt,
			updatedAt
		);
	}
}
