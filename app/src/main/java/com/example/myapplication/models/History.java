package com.example.myapplication.models;

public class History {
    private int imageResRef;
    private String description;
    private String date;
    private String price;

    public History(int imageResRef, String description, String date, String price) {
        this.imageResRef = imageResRef;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public History(int imageResRef, String description, String date) {
        this.imageResRef = imageResRef;
        this.description = description;
        this.date = date;
    }

    public int getImageResRef() {
        return imageResRef;
    }

    public void setImageResRef(int imageResRef) {
        this.imageResRef = imageResRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
