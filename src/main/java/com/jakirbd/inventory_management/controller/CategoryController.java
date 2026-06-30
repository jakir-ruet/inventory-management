package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.CategoryCreateRequest;
import com.jakirbd.inventory_management.dto.CategoryResponse;
import com.jakirbd.inventory_management.dto.CategoryUpdateRequest;
import com.jakirbd.inventory_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{categoryId}")
    public void updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateCategory(@PathVariable Long categoryId) {
        categoryService.deactivateCategory(categoryId);
    }
}
