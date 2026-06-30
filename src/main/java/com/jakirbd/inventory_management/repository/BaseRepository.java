package com.jakirbd.inventory_management.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public abstract class BaseRepository {

    protected final JdbcTemplate jdbcTemplate;

    protected BaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected SimpleJdbcCall createCall(String packageName, String procedureName) {
        return new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(packageName)
            .withProcedureName(procedureName);
    }
}
