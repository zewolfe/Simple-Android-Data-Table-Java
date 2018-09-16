package com.wolfpack.simpleandroiddatatablejava.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Header {
    @Expose
    private String title;
    @Expose
    private List<String> columns;

    public String getTitle() {
        return title;
    }

    public List<String> getColumns() {
        return columns;
    }
}
