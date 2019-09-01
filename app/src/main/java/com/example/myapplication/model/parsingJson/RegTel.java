package com.example.myapplication.model.parsingJson;

public class RegTel {
    private Boolean ok;
    private int errorCode;
    private String description;

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

    public String getDescripton() {
        return description;
    }

    public void setDescripton(String descripton) {
        this.description = descripton;
    }

    @Override
    public String toString() {
        return ("" + ok + " " + errorCode + " " + description);
    }
}
