package com.example.myapplication.presenters;



import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.RegisterIF;

import javax.inject.Inject;


@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterIF> {

    //TODO REGISTER FRAGMENT

    @Inject
    public DataGetter dataGetter;


    public RegisterPresenter() {

    }
}
