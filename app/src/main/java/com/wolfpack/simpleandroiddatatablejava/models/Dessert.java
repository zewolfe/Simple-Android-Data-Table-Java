package com.wolfpack.simpleandroiddatatablejava.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Dessert {
    @Expose
    private String title;
    @Expose
    private List<Float> data;

    public String getTitle() {
        return title;
    }

    public List<Float> getData() {
        return data;
    }
}
