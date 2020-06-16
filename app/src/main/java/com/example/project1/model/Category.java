package com.example.project1.model;

import androidx.annotation.NonNull;

public class Category {

    private String name;
    private int id;
    private int sum;

    public Category(int id,String name) {
        this.id = id;
        this.name = name;
        this.sum = 0;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
