package com.example.demo.questions;

public class Question {
    private int id;
    private String text;
    private double rate;

    Question(){}

    void setId(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    double getRate() {
        return rate;
    }

    void setRate(double rate) {
        this.rate = rate;
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }
}
