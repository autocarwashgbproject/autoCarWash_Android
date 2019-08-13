package com.example.myapplication.adapters;


import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;

public class Menu {

    public static final List<Menu> menuList = Arrays.asList(
            new Menu(R.drawable.ic_payment, "Безналичная оплата"),
            new Menu(R.drawable.menu_bubbles_item, "История моек и платежей"),
            new Menu(R.drawable.menu_settings_item, "Параметры"),
            new Menu(R.drawable.menu_help_item, "Поддержка"),
            new Menu(R.drawable.menu_about_item, "О приложении")
    );

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
