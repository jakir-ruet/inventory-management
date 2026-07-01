package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.ProductRowMapper;
import com.jakirbd.inventory_management.mapper.ProductSupplierRowMapper;
import com.jakirbd.inventory_management.model.Product;
import com.jakirbd.inventory_management.model.ProductSupplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "PRODUCT_PKG";

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CATEGORY_ID", product.getCategoryId());
        params.put("P_PRODUCT_CODE", product.getProductCode());
        params.put("P_PRODUCT_NAME", product.getProductName());
        params.put("P_UNIT_PRICE", product.getUnitPrice());
        params.put("P_REORDER_LEVEL", product.getReorderLevel());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ADD_PRODUCT")
                .execute(params);

        return ((Number) result.get("P_PRODUCT_ID")).longValue();
    }

    public void update(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", product.getProductId());
        params.put("P_CATEGORY_ID", product.getCategoryId());
        params.put("P_PRODUCT_CODE", product.getProductCode());
        params.put("P_PRODUCT_NAME", product.getProductName());
        params.put("P_UNIT_PRICE", product.getUnitPrice());
        params.put("P_REORDER_LEVEL", product.getReorderLevel());
        params.put("P_STATUS", product.getStatus());

        createCall(PACKAGE_NAME, "UPDATE_PRODUCT").execute(params);
    }

    public void deactivate(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        createCall(PACKAGE_NAME, "DEACTIVATE_PRODUCT").execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<Product> findById(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_PRODUCT_BY_ID")
                .returningResultSet("P_RESULT", new ProductRowMapper())
                .execute(params);

        List<Product> products = (List<Product>) result.get("P_RESULT");
        return products.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_PRODUCTS")
                .returningResultSet("P_RESULT", new ProductRowMapper())
                .execute();

        return (List<Product>) result.get("P_RESULT");
    }

    public Long assignSupplier(ProductSupplier productSupplier) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productSupplier.getProductId());
        params.put("P_SUPPLIER_ID", productSupplier.getSupplierId());
        params.put("P_SUPPLIER_PRICE", productSupplier.getSupplierPrice());
        params.put("P_LEAD_TIME_DAYS", productSupplier.getLeadTimeDays());
        params.put("P_IS_PRIMARY_SUPPLIER", productSupplier.getIsPrimarySupplier());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ASSIGN_SUPPLIER")
                .execute(params);

        return ((Number) result.get("P_PRODUCT_SUPPLIER_ID")).longValue();
    }

    @SuppressWarnings("unchecked")
    public List<ProductSupplier> findSuppliersByProductId(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_PRODUCT_SUPPLIERS")
                .returningResultSet("P_RESULT", new ProductSupplierRowMapper())
                .execute(params);

        return (List<ProductSupplier>) result.get("P_RESULT");
    }
}
