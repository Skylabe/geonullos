package com.example.geonullos;

public class Continent {
    private String text;

    private String imageName;

    public Continent(String text, String imageName) {
        this.text = text;
        this.imageName = imageName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageUrl) {
        this.imageName = imageName;
    }


    //getters & setters
}