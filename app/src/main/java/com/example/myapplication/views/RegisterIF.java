package com.example.myapplication.views;


import com.arellomobile.mvp.MvpView;

public interface RegisterIF extends MvpView {
    void showErrorMessage(int message); // or int R.string.error_message

    void loadMain();
}
