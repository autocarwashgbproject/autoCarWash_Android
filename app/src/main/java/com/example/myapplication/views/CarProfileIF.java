package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.api.parsingJson.ApiCar;

import java.util.Map;

public interface CarProfileIF extends MvpView {
    void updateCarsData(Map<String, ApiCar> cars);
}
