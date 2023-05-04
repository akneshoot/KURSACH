package com.example.practica4.model;

import java.util.List;

public interface Datasource<T> {
    List<T> getAll();
    void add(T item);
}