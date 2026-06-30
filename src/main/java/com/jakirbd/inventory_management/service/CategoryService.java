package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.CategoryCreateRequest;
import com.jakirbd.inventory_management.dto.CategoryResponse;
import com.jakirbd.inventory_management.dto.CategoryUpdateRequest;
import com.jakirbd.inventory_management.model.Category;
import com.jakirbd.inventory_management.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Long createCategory(CategoryCreateRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        return categoryRepository.create(category);
    }

    @Transactional
    public void updateCategory(Long categoryId, CategoryUpdateRequest request) {

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());

        categoryRepository.update(category);
    }

    @Transactional
    public void deactivateCategory(Long categoryId) {
        categoryRepository.deactivate(categoryId);
    }

    public CategoryResponse getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found."));

        return mapToResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CategoryResponse mapToResponse(Category category) {

        CategoryResponse response = new CategoryResponse();

        response.setCategoryId(category.getCategoryId());
        response.setCategoryName(category.getCategoryName());
        response.setDescription(category.getDescription());
        response.setStatus(category.getStatus());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        return response;
    }
}
