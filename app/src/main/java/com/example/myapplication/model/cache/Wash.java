package com.example.myapplication.model.cache;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.Date;

@Entity
public class Wash {
    @Expose
    private int id;

    @Expose
    private String washing;

    @Expose
    private Boolean isActive;

    @Expose
    @PrimaryKey
    private long timestamp;

    @Expose
    private int user;

    @Expose
    private int car;

    @Expose
    private int wash;

    public String getWashing() {
        return washing;
    }

    public void setWashing(String washing) {
        this.washing = washing;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getWash() {
        return wash;
    }

    public void setWash(int wash) {
        this.wash = wash;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + isActive + " " + id + " " + new Date(timestamp) + " " + user + " " + car + " " + wash;
    }
}
