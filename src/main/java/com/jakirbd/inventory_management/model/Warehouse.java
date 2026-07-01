package com.jakirbd.inventory_management.model;

import java.time.LocalDateTime;

public class Warehouse {
	private Long warehouseId;
	private String warehouseName;
	private String location;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// This is the default (no-argument) constructor. It allows you to create an empty object:
	// This is the style commonly used in enterprise Spring Boot applications that use JDBC with stored procedures,
	// and it matches your database-first architecture well.
	public Warehouse() {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
			"Warehouse{warehouseId=%d, warehouseName='%s', location='%s', status='%s', createdAt=%s, updatedAt=%s}",
			warehouseId,
			warehouseName,
			location,
			status,
			createdAt,
			updatedAt
		);
	}
}
