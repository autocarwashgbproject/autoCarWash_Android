package com.example.myapplication.model.cache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pay {
    @PrimaryKey
    private int id;

    public Pay(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
