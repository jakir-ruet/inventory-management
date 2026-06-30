package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.PurchaseHeaderRowMapper;
import com.jakirbd.inventory_management.model.PurchaseHeader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PurchaseHeaderRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "PURCHASE_PKG";

    public PurchaseHeaderRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(PurchaseHeader purchaseHeader) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SUPPLIER_ID", purchaseHeader.getSupplierId());
        params.put("P_WAREHOUSE_ID", purchaseHeader.getWarehouseId());
        params.put("P_INVOICE_NO", purchaseHeader.getInvoiceNo());

        Map<String, Object> result = createCall(PACKAGE_NAME, "CREATE_PURCHASE")
                .execute(params);

        return ((Number) result.get("P_PURCHASE_ID")).longValue();
    }

    public void confirm(Long purchaseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PURCHASE_ID", purchaseId);

        createCall(PACKAGE_NAME, "CONFIRM_PURCHASE").execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<PurchaseHeader> findById(Long purchaseId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_PURCHASE_ID", purchaseId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_PURCHASE_BY_ID")
                .returningResultSet("P_RESULT", new PurchaseHeaderRowMapper())
                .execute(params);

        List<PurchaseHeader> purchases = (List<PurchaseHeader>) result.get("P_RESULT");

        return purchases.stream().findFirst();
    }
}
