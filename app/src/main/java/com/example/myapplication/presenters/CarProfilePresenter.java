package com.example.myapplication.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.model.cache.RoomCache;
import com.example.myapplication.views.CarProfileIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class CarProfilePresenter extends MvpPresenter<CarProfileIF> {

    @Inject
    public DataGetter dataGetter;

    @Inject
    public RoomCache roomCache;

    private CompositeDisposable disposable;

    public CarProfilePresenter() {
        disposable = new CompositeDisposable();
    }

    public void createCar(String carNumber) {
        disposable.add(dataGetter
                .createCar(carNumber.toUpperCase())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                }, throwable -> {
                }));
    }

    public void dispose() {
        disposable.dispose();
    }
}
