package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.StockTransactionRowMapper;
import com.jakirbd.inventory_management.model.StockTransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockTransactionRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "INVENTORY_PKG";

    public StockTransactionRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @SuppressWarnings("unchecked")
    public List<StockTransaction> findByProductId(Long productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PRODUCT_ID", productId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_STOCK_TRANSACTIONS")
                .returningResultSet("P_RESULT", new StockTransactionRowMapper())
                .execute(params);

        return (List<StockTransaction>) result.get("P_RESULT");
    }
}
