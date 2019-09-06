package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;

public interface WashIF extends MvpView {

    void updateClientData(ApiClient client);

    void updateCarData(ApiCar car);
}
