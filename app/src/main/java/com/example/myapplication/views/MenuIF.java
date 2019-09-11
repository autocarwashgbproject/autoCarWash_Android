package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiClient;

public interface MenuIF extends MvpView {
    void updateProfile(ApiClient client);
}
