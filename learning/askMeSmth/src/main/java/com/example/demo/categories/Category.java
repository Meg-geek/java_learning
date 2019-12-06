package com.example.demo.categories;

public class Category {
    private int id;
    private String categoryName;

    Category(){}

    Category(int id, String categoryName){
        this.id = id;
        this.categoryName = categoryName;
    }

    int getId() {
        return id;
    }

    String getCategoryName() {
        return categoryName;
    }

    void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    void setId(int id) {
        this.id = id;
    }
}
