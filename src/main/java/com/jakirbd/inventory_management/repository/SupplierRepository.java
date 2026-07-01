package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.SupplierRowMapper;
import com.jakirbd.inventory_management.model.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SupplierRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "SUPPLIER_PKG";

    public SupplierRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(Supplier supplier) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SUPPLIER_NAME", supplier.getSupplierName());
        params.put("P_CONTACT_PERSON", supplier.getContactPerson());
        params.put("P_PHONE", supplier.getPhone());
        params.put("P_EMAIL", supplier.getEmail());
        params.put("P_ADDRESS", supplier.getAddress());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ADD_SUPPLIER")
                .execute(params);

        return ((Number) result.get("P_SUPPLIER_ID")).longValue();
    }

    public void update(Supplier supplier) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SUPPLIER_ID", supplier.getSupplierId());
        params.put("P_SUPPLIER_NAME", supplier.getSupplierName());
        params.put("P_CONTACT_PERSON", supplier.getContactPerson());
        params.put("P_PHONE", supplier.getPhone());
        params.put("P_EMAIL", supplier.getEmail());
        params.put("P_ADDRESS", supplier.getAddress());
        params.put("P_STATUS", supplier.getStatus());

        createCall(PACKAGE_NAME, "UPDATE_SUPPLIER").execute(params);
    }

    public void deactivate(Long supplierId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SUPPLIER_ID", supplierId);

        createCall(PACKAGE_NAME, "DEACTIVATE_SUPPLIER").execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<Supplier> findById(Long supplierId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_SUPPLIER_ID", supplierId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_SUPPLIER_BY_ID")
                .returningResultSet("P_RESULT", new SupplierRowMapper())
                .execute(params);

        List<Supplier> suppliers = (List<Supplier>) result.get("P_RESULT");
        return suppliers.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Supplier> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_SUPPLIERS")
                .returningResultSet("P_RESULT", new SupplierRowMapper())
                .execute();

        return (List<Supplier>) result.get("P_RESULT");
    }
}
