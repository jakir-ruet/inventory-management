package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.model.SalesLine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SalesLineRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "SALES_PKG";

    public SalesLineRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void addLine(SalesLine salesLine) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SALES_ID", salesLine.getSalesId());
        params.put("P_PRODUCT_ID", salesLine.getProductId());
        params.put("P_QUANTITY", salesLine.getQuantity());
        params.put("P_UNIT_PRICE", salesLine.getUnitPrice());

        createCall(PACKAGE_NAME, "ADD_SALES_LINE")
                .execute(params);
    }
}
