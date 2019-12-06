package com.example.demo.question_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionCategoryRelationService {
    private QuestionCategoryRealtionRepository realtionRepository;

    @Autowired
    public QuestionCategoryRelationService(QuestionCategoryRealtionRepository realtionRepository){
        this.realtionRepository = realtionRepository;
    }

    public void addRelation(int questionId, int categoryId){
        realtionRepository.addRelation(questionId, categoryId);
    }
}
