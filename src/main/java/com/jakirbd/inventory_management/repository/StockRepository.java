package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.StockRowMapper;
import com.jakirbd.inventory_management.model.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "INVENTORY_PKG";

    public StockRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void initializeStock(Stock stock) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", stock.getProductId());
        params.put("P_WAREHOUSE_ID", stock.getWarehouseId());
        params.put("P_QUANTITY", stock.getQuantity());

        createCall(PACKAGE_NAME, "INITIALIZE_STOCK").execute(params);
    }

    @SuppressWarnings("unchecked")
    public List<Stock> findByProductId(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_STOCK_BY_PRODUCT")
                .returningResultSet("P_RESULT", new StockRowMapper())
                .execute(params);

        return (List<Stock>) result.get("P_RESULT");
    }

    @SuppressWarnings("unchecked")
    public List<Stock> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_STOCK")
                .returningResultSet("P_RESULT", new StockRowMapper())
                .execute();

        return (List<Stock>) result.get("P_RESULT");
    }
}
