package com.example.myapplication.model.api.parsingJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;
import java.util.TreeSet;

public class Registration {

    @SerializedName("ok")
    @Expose
    private boolean ok;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("is_registered")
    @Expose
    private boolean isRegistered;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("cars_id")
    @Expose
    private Set<Integer> carsId = new TreeSet<>();

    public Registration() {
    }

    @Override
    public String toString() {
        return "Registration{" +
                "ok=" + ok +
                ", id=" + id +
                ", isRegistered=" + isRegistered +
                ", phone=" + phone +
                ", token='" + token + '\'' +
                ", carsId=" + carsId +
                '}';
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setCarsId(Set<Integer> carsId) {
        this.carsId = carsId;
    }

    public Set<Integer> getCarsId() {
        return carsId;
    }
}
