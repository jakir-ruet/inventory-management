package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.SalesHeaderRowMapper;
import com.jakirbd.inventory_management.model.SalesHeader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SalesHeaderRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "SALES_PKG";

    public SalesHeaderRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(SalesHeader salesHeader) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_BUYER_ID", salesHeader.getBuyerId());
        params.put("P_WAREHOUSE_ID", salesHeader.getWarehouseId());
        params.put("P_INVOICE_NO", salesHeader.getInvoiceNo());

        Map<String, Object> result = createCall(PACKAGE_NAME, "CREATE_SALE")
                .execute(params);

        return ((Number) result.get("P_SALES_ID")).longValue();
    }

    public void confirm(Long salesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SALES_ID", salesId);

        createCall(PACKAGE_NAME, "CONFIRM_SALE")
                .execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<SalesHeader> findById(Long salesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SALES_ID", salesId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_SALE_BY_ID")
                .returningResultSet("P_RESULT", new SalesHeaderRowMapper())
                .execute(params);

        List<SalesHeader> salesHeaders =
                (List<SalesHeader>) result.get("P_RESULT");

        return salesHeaders.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<SalesHeader> findAll() {

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_SALES")
                .returningResultSet("P_RESULT", new SalesHeaderRowMapper())
                .execute();

        return (List<SalesHeader>) result.get("P_RESULT");
    }
}
