package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;

import java.util.Map;

public interface WashIF extends MvpView {

    void updateClientData(ApiClient client);

    void updateCarsData(Map<Integer, ApiCar> cars);
}
