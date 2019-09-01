package com.example.myapplication.model.parsingJson;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class RegClient {
    @Expose
    private Boolean ok;
    @Expose
    private String token;
    @Expose
    private String idClient;
    @Expose
    private Boolean isRegistered;
    @Expose
    private String phone;
    @Expose
    private int errorCode;
    @Expose
    private String description;

    @NonNull
    @Override
    public String toString() {
        return "" + ok + " "
                + idClient + " "
                + token + " "
                + isRegistered + " "
                + phone + " "
                + errorCode + " "
                + description;
    }

    public Boolean getOk() {
        return ok;
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

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
