package com.jakirbd.inventory_management.repository;

import com.jakirbd.inventory_management.mapper.CategoryRowMapper;
import com.jakirbd.inventory_management.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryRepository extends BaseRepository {

    private static final String PACKAGE_NAME = "CATEGORY_PKG";

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Long create(Category category) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CATEGORY_NAME", category.getCategoryName());
        params.put("P_DESCRIPTION", category.getDescription());

        Map<String, Object> result = createCall(PACKAGE_NAME, "ADD_CATEGORY")
                .execute(params);

        return ((Number) result.get("P_CATEGORY_ID")).longValue();
    }

    public void update(Category category) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CATEGORY_ID", category.getCategoryId());
        params.put("P_CATEGORY_NAME", category.getCategoryName());
        params.put("P_DESCRIPTION", category.getDescription());
        params.put("P_STATUS", category.getStatus());

        createCall(PACKAGE_NAME, "UPDATE_CATEGORY")
                .execute(params);
    }

    public void deactivate(Long categoryId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CATEGORY_ID", categoryId);

        createCall(PACKAGE_NAME, "DEACTIVATE_CATEGORY")
                .execute(params);
    }

    @SuppressWarnings("unchecked")
    public Optional<Category> findById(Long categoryId) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_CATEGORY_ID", categoryId);

        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_CATEGORY_BY_ID")
                .returningResultSet("P_RESULT", new CategoryRowMapper())
                .execute(params);

        List<Category> categories = (List<Category>) result.get("P_RESULT");

        return categories.stream().findFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        Map<String, Object> result = createCall(PACKAGE_NAME, "GET_ALL_CATEGORIES")
                .returningResultSet("P_RESULT", new CategoryRowMapper())
                .execute();

        return (List<Category>) result.get("P_RESULT");
    }
}
