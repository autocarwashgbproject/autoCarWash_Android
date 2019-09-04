package com.example.myapplication.model.cache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wash {
    private String status;
    private Boolean active;
    @PrimaryKey
    private long washTime;
    private int userId;
    private int carId;
    private int wash;

    public Wash(String status, Boolean active, long washTime, int userId, int carId, int wash) {
        this.status = status;
        this.active = active;
        this.washTime = washTime;
        this.userId = userId;
        this.carId = carId;
        this.wash = wash;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getActive() {
        return active;
    }

    public long getWashTime() {
        return washTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarId() {
        return carId;
    }

    public int getWash() {
        return wash;
    }
}
