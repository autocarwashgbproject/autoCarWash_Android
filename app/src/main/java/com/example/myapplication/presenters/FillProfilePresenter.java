package com.example.myapplication.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.FillProfileIF;

import javax.inject.Inject;

@InjectViewState
public class FillProfilePresenter extends MvpPresenter<FillProfileIF> {

    @Inject
    public DataGetter dataGetter;

    public FillProfilePresenter() {}

}
