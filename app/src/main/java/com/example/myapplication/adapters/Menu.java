package com.example.myapplication.adapters;


public class Menu {

    private int img;
    private String title;

    public Menu() {
    }

    public Menu(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }
}
