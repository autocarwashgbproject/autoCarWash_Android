package com.example.myapplication.model.parsingJson;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class ApiCar {
    @Expose(serialize = false)
    private String ok;

    @Expose(serialize = false)
    private int id;

    @Expose
    private String regNum;

    @Expose(serialize = false)
    private int timestamp;

    @Expose(serialize = false)
    private int updateDate;

    @Expose(serialize = false)
    private String description;

    public void setOk(String ok) {
        this.ok = ok;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setUpdateDate(int updateDate) {
        this.updateDate = updateDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + id + "\n"
                + "ok: " + ok + "\n"
                + "regNum: " + regNum + "\n"
                + "timestamp: " + timestamp + "\n"
                + "updateDate: " + updateDate + "\n"
                + "description: " + description;
    }
}
