package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class PurchaseHeader {

	private Long purchaseId;
	private Long supplierId;
	private String supplierName;
	private Long warehouseId;
	private String warehouseName;
	private LocalDateTime purchaseDate;
	private String invoiceNo;
	private Double totalAmount;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.

	public PurchaseHeader() {
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
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

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
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
			"PurchaseHeader{purchaseId=%d, supplierId=%d, supplierName='%s', warehouseId=%d, warehouseName='%s', purchaseDate=%s, invoiceNo='%s', totalAmount=%.2f, status='%s', createdAt=%s, updatedAt=%s}",
			purchaseId,
			supplierId,
			supplierName,
			warehouseId,
			warehouseName,
			purchaseDate,
			invoiceNo,
			totalAmount,
			status,
			createdAt,
			updatedAt
		);
	}
}

// Note: supplierName and warehouseName are not physical columns in purchase_headers;
// they are populated from the joined query in PURCHASE_PKG.GET_PURCHASE_BY_ID.
