package com.jakirbd.inventory_management.dto;

import java.time.LocalDateTime;

public class CategoryResponse {

	private Long categoryId;
	private String categoryName;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public CategoryResponse() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
			"CategoryResponse{categoryId=%d, categoryName='%s', description='%s', status='%s', createdAt=%s, updatedAt=%s}",
			categoryId,
			categoryName,
			description,
			status,
			createdAt,
			updatedAt
		);
	}
}
