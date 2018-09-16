package com.wolfpack.simpleandroiddatatablejava.models;

import com.google.gson.annotations.Expose;

public class Data {
    @Expose
    private Header header;
    @Expose
    private Dessert[] data;

    public Header getHeader() {
        return header;
    }

    public Dessert[] getData() {
        return data;
    }
}
