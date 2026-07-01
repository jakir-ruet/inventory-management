package com.jakirbd.inventory_management.service;

import com.jakirbd.inventory_management.dto.ProductCreateRequest;
import com.jakirbd.inventory_management.dto.ProductResponse;
import com.jakirbd.inventory_management.dto.ProductUpdateRequest;
import com.jakirbd.inventory_management.model.Product;
import com.jakirbd.inventory_management.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Long createProduct(ProductCreateRequest request) {
        Product product = new Product();

        product.setCategoryId(request.getCategoryId());
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setUnitPrice(request.getUnitPrice());
        product.setReorderLevel(request.getReorderLevel());

        return productRepository.create(product);
    }

    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = new Product();

        product.setProductId(productId);
        product.setCategoryId(request.getCategoryId());
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setUnitPrice(request.getUnitPrice());
        product.setReorderLevel(request.getReorderLevel());
        product.setStatus(request.getStatus());

        productRepository.update(product);
    }

    @Transactional
    public void deactivateProduct(Long productId) {
        productRepository.deactivate(productId);
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        return mapToResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setProductId(product.getProductId());
        response.setCategoryId(product.getCategoryId());
        response.setCategoryName(product.getCategoryName());
        response.setProductCode(product.getProductCode());
        response.setProductName(product.getProductName());
        response.setUnitPrice(product.getUnitPrice());
        response.setReorderLevel(product.getReorderLevel());
        response.setStatus(product.getStatus());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }
}
