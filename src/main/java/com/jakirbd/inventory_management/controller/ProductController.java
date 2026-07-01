package com.jakirbd.inventory_management.controller;

import com.jakirbd.inventory_management.dto.ProductCreateRequest;
import com.jakirbd.inventory_management.dto.ProductResponse;
import com.jakirbd.inventory_management.dto.ProductUpdateRequest;
import com.jakirbd.inventory_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductUpdateRequest request) {

        productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateProduct(@PathVariable Long productId) {
        productService.deactivateProduct(productId);
    }
}
