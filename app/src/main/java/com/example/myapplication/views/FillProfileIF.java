package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiClient;

public interface FillProfileIF extends MvpView {
    void updateData(ApiClient client);

}
