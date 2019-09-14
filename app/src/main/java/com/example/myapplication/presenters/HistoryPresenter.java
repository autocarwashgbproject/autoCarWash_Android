package com.example.myapplication.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.HistoryIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryIF> {

    @Inject
    public DataGetter dataGetter;

    public void getHistoryList() {
        dataGetter.getWashHistiry()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (!result.isEmpty()) getViewState().updateList(result);
                });
    }

    public void addWash() {
        dataGetter.addWash();
    }
}
