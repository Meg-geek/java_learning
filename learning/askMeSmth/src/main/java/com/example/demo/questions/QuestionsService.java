package com.example.demo.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {
    private QuestionsRepository repository;
    public static final int WRONG_INSERT = QuestionsRepository.WRONG_INSERT;

    @Autowired
    public QuestionsService(QuestionsRepository repository){
        this.repository = repository;
    }

    public void addQuestion(String questionText){
        repository.addQuestion(questionText);
    }

    public int insertReturningID(String questionText){
        return repository.insertReturningID(questionText);
    }
}
