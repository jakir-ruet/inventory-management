package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class SalesHeader {

	private Long salesId;
	private Long buyerId;
	private String buyerName;
	private Long warehouseId;
	private String warehouseName;
	private LocalDateTime salesDate;
	private String invoiceNo;
	private Double totalAmount;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.
	
	public SalesHeader() {
	}

	public Long getSalesId() {
		return salesId;
	}

	public void setSalesId(Long salesId) {
		this.salesId = salesId;
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

	public LocalDateTime getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(LocalDateTime salesDate) {
		this.salesDate = salesDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
			"SalesHeader{salesId=%d, buyerId=%d, buyerName='%s', warehouseId=%d, warehouseName='%s', salesDate=%s, invoiceNo='%s', totalAmount=%s, status='%s', createdAt=%s, updatedAt=%s}",
			salesId,
			buyerId,
			buyerName,
			warehouseId,
			warehouseName,
			salesDate,
			invoiceNo,
			totalAmount,
			status,
			createdAt,
			updatedAt
		);
	}
}

// buyerName and warehouseName are joined/display fields, not physical columns in sales_headers.
