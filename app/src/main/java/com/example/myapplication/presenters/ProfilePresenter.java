package com.example.myapplication.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.views.ProfileIF;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileIF> {

    public ProfilePresenter() {
    }
}
