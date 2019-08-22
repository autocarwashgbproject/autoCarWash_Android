package com.example.myapplication.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.views.FillProfileIF;

@InjectViewState
public class FillProfilePresenter extends MvpPresenter<FillProfileIF> {

    public FillProfilePresenter() {}


    public void loadImage() {
        // TODO: 2019-08-21  Загрузка из базы данных ссылки на аватарку.
        // String url;
        getViewState().setAvatarImage(/*url*/);
    }
}
