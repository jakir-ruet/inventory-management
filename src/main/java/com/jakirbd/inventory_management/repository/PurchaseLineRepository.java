package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.model.PurchaseLine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PurchaseLineRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "PURCHASE_PKG";

    public PurchaseLineRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void addLine(PurchaseLine purchaseLine) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PURCHASE_ID", purchaseLine.getPurchaseId());
        params.put("P_PRODUCT_ID", purchaseLine.getProductId());
        params.put("P_QUANTITY", purchaseLine.getQuantity());
        params.put("P_UNIT_COST", purchaseLine.getUnitCost());

        createCall(PACKAGE_NAME, "ADD_PURCHASE_LINE")
                .execute(params);
    }
}
