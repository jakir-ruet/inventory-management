package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.ProductSupplierRowMapper;
import com.jakirbd.inventory_management.model.ProductSupplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductSupplierRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "PRODUCT_PKG";

    public ProductSupplierRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
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
    public List<ProductSupplier> findByProductId(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_PRODUCT_SUPPLIERS")
                .returningResultSet("P_RESULT", new ProductSupplierRowMapper())
                .execute(params);

        return (List<ProductSupplier>) result.get("P_RESULT");
    }
}
// Since PRODUCT_PKG already manages product-supplier mapping, ProductSupplierRepository should call the same package.
// Note: if you keep this separate repository, remove assignSupplier() and findSuppliersByProductId() from ProductRepository to avoid duplicate responsibility.
