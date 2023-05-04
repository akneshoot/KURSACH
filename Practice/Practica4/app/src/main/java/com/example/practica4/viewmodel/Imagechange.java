package com.example.practica4.viewmodel;

public class Imagechange {
    private int current_image;


    public Imagechange(int current_image) {
        this.current_image = current_image;
    }

    public void changeImage(int newImage){
        current_image = newImage;
    }

    public int getCurrentImage(){
        return current_image;
    }
}
