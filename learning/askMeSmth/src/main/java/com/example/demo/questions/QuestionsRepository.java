package com.example.demo.questions;

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
public class QuestionsRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "question";
    private final String TEXT_PARAMETER_NAME = "text";
    private final String SQL_INSERT = "insert into" + TABLE_NAME +"question  (text)" +
            "values (:" + TEXT_PARAMETER_NAME + ")";
    private final String SELECT_QUESTION_BY_TEXT ="select * from " +
            TABLE_NAME + "where text ILIKE :" + TEXT_PARAMETER_NAME;
    private ResultSetExtractor<List<Question>> questionExtractor = new QuestionExtractor();


    @Autowired
    public QuestionsRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestion(String questionText){
        if(!isQuestionExists(questionText)){
            MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                    .addValue(TEXT_PARAMETER_NAME, questionText);
            jdbcTemplate.update(SQL_INSERT, parameterMap);
        }
    }

    public boolean isQuestionExists(String questionText){
        MapSqlParameterSource parameterMap = new MapSqlParameterSource()
                .addValue(TEXT_PARAMETER_NAME, questionText);
        List<Question> selectedQuestionList = jdbcTemplate.query(SELECT_QUESTION_BY_TEXT,
                parameterMap, questionExtractor);
        if(selectedQuestionList == null){
            return false;
        }
        return (selectedQuestionList.size() > 0);
    }
}

class QuestionExtractor implements ResultSetExtractor<List<Question>> {

    @Override
    public List<Question> extractData(ResultSet resultSet) throws SQLException,
            DataAccessException {
        List<Question> questionList = new ArrayList<>();
        while(resultSet.next()){
            Question question = new Question();
            question.setId(resultSet.getInt("id"));
            question.setText(resultSet.getString("text"));
            question.setRate(resultSet.getDouble("rate"));
            questionList.add(question);
        }
        return questionList;
    }
}
