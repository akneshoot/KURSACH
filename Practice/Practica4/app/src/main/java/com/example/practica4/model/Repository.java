package com.example.practica4.model;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private final Datasource<T> arrayDataSource;

    public Repository(Datasource<T> arrayDataSource) {
        this.arrayDataSource = arrayDataSource;
    }

    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        result.addAll(arrayDataSource.getAll());
        return result;
    }

    public void add(T item) {
        arrayDataSource.add(item);

    }

}