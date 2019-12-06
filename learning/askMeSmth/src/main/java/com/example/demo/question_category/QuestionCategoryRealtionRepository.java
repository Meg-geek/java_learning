package com.example.demo.question_category;

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
public class QuestionCategoryRealtionRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "question_category";
    static final String FIRST_COLUMN = "question_id";
    static final String SECOND_COLUMN = "category_id";
    private final String SQL_INSERT = "insert into " + TABLE_NAME
            + "(" + FIRST_COLUMN + ", " + SECOND_COLUMN + ") values ("
            + ":" + FIRST_COLUMN + ", :" + SECOND_COLUMN + ")";
    private final String SELECT_RELATION = "select * from " + TABLE_NAME
            + " where " + FIRST_COLUMN + "= :" + FIRST_COLUMN + " AND " +
            SECOND_COLUMN + "= :" + SECOND_COLUMN;
    private ResultSetExtractor<List<QuestionCategoryRelation>> relationExtractor =
            new QuestionCategoryExtractor();

    @Autowired
    public QuestionCategoryRealtionRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    void addRelation(int questionId, int categoryId){
        if(!hasRelation(questionId, categoryId)){
            MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                    .addValue(FIRST_COLUMN, questionId)
                    .addValue(SECOND_COLUMN, categoryId);
            jdbcTemplate.update(SQL_INSERT, parameterMap);
        }
    }

    boolean hasRelation(int questionId, int categoryId){
        MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                .addValue(FIRST_COLUMN, questionId)
                .addValue(SECOND_COLUMN, categoryId);
        List<QuestionCategoryRelation> selectedRelationList = jdbcTemplate.query(SELECT_RELATION,
                parameterMap, relationExtractor);
        if(selectedRelationList == null){
            return false;
        }
        return (selectedRelationList.size() > 0);
    }
}

class QuestionCategoryExtractor implements ResultSetExtractor<List<QuestionCategoryRelation>>{

    @Override
    public List<QuestionCategoryRelation> extractData(ResultSet resultSet)
            throws SQLException, DataAccessException {
        List<QuestionCategoryRelation> relationList = new ArrayList<>();
        while(resultSet.next()){
            QuestionCategoryRelation relation = new QuestionCategoryRelation();
            relation.setQuestionId(resultSet.getInt(QuestionCategoryRealtionRepository.FIRST_COLUMN));
            relation.setCategoryId(resultSet.getInt(QuestionCategoryRealtionRepository.SECOND_COLUMN));
            relationList.add(relation);
        }
        return relationList;
    }
}