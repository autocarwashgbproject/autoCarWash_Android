package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;

import java.util.Map;

public interface ProfileIF extends MvpView {
    void updateData(ApiClient client);

    void updateCarsData(Map<Integer, ApiCar> cars);
}
