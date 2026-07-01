package com.jakirbd.inventory_management.mapper;

import com.jakirbd.inventory_management.model.Buyer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BuyerRowMapper implements RowMapper<Buyer> {

    @Override
    public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Buyer buyer = new Buyer();

        buyer.setBuyerId(rs.getLong("BUYER_ID"));
        buyer.setBuyerName(rs.getString("BUYER_NAME"));
        buyer.setContactPerson(rs.getString("CONTACT_PERSON"));
        buyer.setPhone(rs.getString("PHONE"));
        buyer.setEmail(rs.getString("EMAIL"));
        buyer.setAddress(rs.getString("ADDRESS"));
        buyer.setStatus(rs.getString("STATUS"));

        Timestamp createdAt = rs.getTimestamp("CREATED_AT");
        if (createdAt != null) {
            buyer.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
        if (updatedAt != null) {
            buyer.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return buyer;
    }
}
