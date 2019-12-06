package com.example.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionCategoriesRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String INSERT_PARAMETER_NAME = "name";
    private final String SQL_INSERT = "insert into category  (name)" +
            "values (:" + INSERT_PARAMETER_NAME + ")";

    private ResultSetExtractor<List<Category>> extractor = new CategoryExtractor();
    static final int CATEGORY_NOT_FOUND = -1;

    @Autowired
    public QuestionCategoriesRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    void addCategory(String questionCategoryName){
        if(!isCategoryExists(questionCategoryName)){
            MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                    .addValue(INSERT_PARAMETER_NAME, questionCategoryName);
            jdbcTemplate.update(SQL_INSERT, parameterMap);
        }
    }

    boolean isCategoryExists(String categoryName){
        String sqlSelect = "select * from category where name ILIKE :category";
        MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                .addValue("category", categoryName);
        List<Category> selectedList = jdbcTemplate
                .query(sqlSelect, parameterMap, extractor);
        if(selectedList == null){
            return false;
        }
        return (selectedList.size() > 0);
    }

    int getCategoryID(String categoryName){
        if(!isCategoryExists(categoryName)){
            return CATEGORY_NOT_FOUND;
        }
        String sqlSelect = "select * from category where name ILIKE :category";
        MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                .addValue("category", categoryName);
        List<Category> selectedList = jdbcTemplate
                .query(sqlSelect, parameterMap, extractor);
        if(selectedList == null){
            return CATEGORY_NOT_FOUND;
        }
        return selectedList.get(0).getId();
    }
}

class CategoryExtractor implements ResultSetExtractor<List<Category>>{

    @Override
    public List<Category> extractData(ResultSet resultSet) throws SQLException,
            DataAccessException {
        List<Category> categoryList = new ArrayList<>();
        while(resultSet.next()){
            Category category = new Category();
            category.setCategoryName(resultSet.getString("name"));
            category.setId(resultSet.getInt("id"));
            categoryList.add(category);
        }
        return categoryList;
    }
}
