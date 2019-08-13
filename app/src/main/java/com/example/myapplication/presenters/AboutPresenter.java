package com.example.myapplication.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.views.AboutIF;

@InjectViewState
public class AboutPresenter extends MvpPresenter<AboutIF> {

    public AboutPresenter() {
    }

    public void showAgreement() {
        Log.d("TAGG", " Agreement ");
    }

    public void showPrivacyPolicy() {
        Log.d("TAGG", " showPrivacyPolicy ");
    }
}
