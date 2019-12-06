package com.example.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class QuestionCategoriesService {
    private QuestionCategoriesRepository repository;

    @Autowired
    public QuestionCategoriesService(QuestionCategoriesRepository repository){
        this.repository = repository;
    }

    public void addCategory(String categoryName){
        repository.addCategory(categoryName);
    }
}
