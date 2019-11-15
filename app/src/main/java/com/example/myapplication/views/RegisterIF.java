package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface RegisterIF extends MvpView {
    void showErrorMessage(int message);

    @StateStrategyType(SkipStrategy.class)
    void loadMain();

    void fillCodeFields(String smsForTests);

    void saveRegistrationStatus();
}
