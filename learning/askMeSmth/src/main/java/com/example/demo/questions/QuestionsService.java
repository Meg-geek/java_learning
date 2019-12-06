package com.example.demo.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService {
    private QuestionsRepository repository;

    @Autowired
    public QuestionsService(QuestionsRepository repository){
        this.repository = repository;
    }

    public void addQuestion(String questionText){
        repository.addQuestion(questionText);
    }
}
