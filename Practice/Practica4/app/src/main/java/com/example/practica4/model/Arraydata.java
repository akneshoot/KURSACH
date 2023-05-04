package com.example.practica4.model;

import java.util.ArrayList;
import java.util.List;

public class Arraydata<T> implements Datasource<T> {
    private List<T> data = new ArrayList<>();

    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public void add(T item) {
        data.add(item);
    }

}