package com.jakirbd.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoryUpdateRequest {

	@NotBlank(message = "Category name is required.")
	@Size(max = 100, message = "Category name must not exceed 100 characters.")
	private String categoryName;

	@Size(max = 500, message = "Description must not exceed 500 characters.")
	private String description;

	@NotBlank(message = "Status is required.")
	@Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be either ACTIVE or INACTIVE.")
	private String status;

	public CategoryUpdateRequest() {
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

	@Override
	public String toString() {
		return String.format(
				"CategoryUpdateRequest{categoryName='%s', description='%s', status='%s'}",
				categoryName,
				description,
				status);
	}
}

// Since your CATEGORY_PKG.UPDATE_CATEGORY accepts

// p_category_id   -- from URL path
// p_category_name
// p_description
// p_status

// the categoryId should not be part of the DTO. It will come from the REST path variable:

// PUT /api/categories/{categoryId}

// Spring Boot will receive //
// categoryId → @PathVariable Long categoryId
// CategoryUpdateRequest → @RequestBody

// This is the standard RESTful design and aligns perfectly with your database-first approach.

