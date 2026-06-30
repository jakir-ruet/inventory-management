package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.BuyerRowMapper;
import com.jakirbd.inventory_management.model.Buyer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BuyerRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "BUYER_PKG";

    public BuyerRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(Buyer buyer) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_BUYER_NAME", buyer.getBuyerName());
        params.put("P_CONTACT_PERSON", buyer.getContactPerson());
        params.put("P_PHONE", buyer.getPhone());
        params.put("P_EMAIL", buyer.getEmail());
        params.put("P_ADDRESS", buyer.getAddress());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ADD_BUYER")
                .execute(params);

        return ((Number) result.get("P_BUYER_ID")).longValue();
    }

    public void update(Buyer buyer) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_BUYER_ID", buyer.getBuyerId());
        params.put("P_BUYER_NAME", buyer.getBuyerName());
        params.put("P_CONTACT_PERSON", buyer.getContactPerson());
        params.put("P_PHONE", buyer.getPhone());
        params.put("P_EMAIL", buyer.getEmail());
        params.put("P_ADDRESS", buyer.getAddress());
        params.put("P_STATUS", buyer.getStatus());

        createCall(PACKAGE_NAME, "UPDATE_BUYER").execute(params);
    }

    public void deactivate(Long buyerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_BUYER_ID", buyerId);

        createCall(PACKAGE_NAME, "DEACTIVATE_BUYER").execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<Buyer> findById(Long buyerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_BUYER_ID", buyerId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_BUYER_BY_ID")
                .returningResultSet("P_RESULT", new BuyerRowMapper())
                .execute(params);

        List<Buyer> buyers = (List<Buyer>) result.get("P_RESULT");
        return buyers.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Buyer> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_BUYERS")
                .returningResultSet("P_RESULT", new BuyerRowMapper())
                .execute();

        return (List<Buyer>) result.get("P_RESULT");
    }
}
