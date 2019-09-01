package com.example.myapplication.model.parsingJson;

import com.google.gson.annotations.Expose;

public class RegTel {
    @Expose
    private Boolean ok;
    @Expose
    private String phone;
    @Expose
    private String smsForTests;
    @Expose
    private int errorCode;
    @Expose
    private String description;

    /*public RegTel(Boolean ok, int errorCode, String description) {
        this.ok = ok;
        this.errorCode = errorCode;
        this.description = description;
    }*/

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsForTests() {
        return smsForTests;
    }

    public void setSmsForTests(String smsForTests) {
        this.smsForTests = smsForTests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return ("" + ok + " " + phone + " " + smsForTests + " " + errorCode + " " + description);
    }
}
