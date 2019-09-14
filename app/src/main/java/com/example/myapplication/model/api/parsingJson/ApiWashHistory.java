package com.example.myapplication.model.api.parsingJson;

import com.example.myapplication.model.cache.Wash;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ApiWashHistory {

    @Expose
    private String ok;

    @Expose
    private List<Wash> washing = new ArrayList<>();

    public List<Wash> getWashes() {
        return washing;
    }

    public void setWashes(List<Wash> washing) {
        this.washing = washing;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
