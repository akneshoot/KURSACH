package com.example.practica4.viewmodel;
public class List {
    private int image;
    private String title;
    private int button;

    public List(int image, String title, int button) {
        this.image = image;
        this.title = title;
        this.button = button;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getButton() {
        return button;
    }
}

