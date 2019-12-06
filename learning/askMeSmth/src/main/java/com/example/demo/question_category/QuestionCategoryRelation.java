package com.example.demo.question_category;

class QuestionCategoryRelation {
    private int questionId, categoryId;

    QuestionCategoryRelation(){}

    QuestionCategoryRelation(int questionId, int categoryId){
        this.questionId =  questionId;
        this.categoryId = categoryId;
    }

    void setQuestionId(int questionId){
        this.questionId =  questionId;
    }

    void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    int getQuestionId(){
        return questionId;
    }

    int getCategoryId(){
        return categoryId;
    }
}
