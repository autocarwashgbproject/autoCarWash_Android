package com.example.myapplication.model.parsingJson;

import androidx.annotation.NonNull;

public class RegUser {
    private Boolean ok;
    private String token;
    private int id;
    private Boolean isRegistered;
    private int phone;
    private int errorCode;
    private String description;

    public Boolean getOk() {
        return ok;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + ok + " "
                + token + " "
                + isRegistered + " "
                + phone + " "
                + errorCode + " "
                + description;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
