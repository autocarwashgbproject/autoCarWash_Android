package com.example.myapplication.models;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/*Загушка*/

public class HistoryList {
    private static HistoryList ourInstance = new HistoryList();

    List<History> histories;

    public static HistoryList getInstance() {
        if (ourInstance == null) {
            ourInstance = new HistoryList();
            return ourInstance;
        } else {
            return ourInstance;
        }
    }

    public List<History> getHistories() {
        return histories;
    }

    private HistoryList() {
        initList();
    }

    private void initList() {
        histories = new ArrayList<>();
        histories.add(new History(R.drawable.menu_bubbles_item, "О 999 ОК 60 RUS",
                "Пн, 15 июл, 15:30"));
        histories.add(new History(R.drawable.ic_payment, "О 999 ОК 60 RUS",
                "Чт, 10 июл, 19:22", "2000 Р"));

    }

}
