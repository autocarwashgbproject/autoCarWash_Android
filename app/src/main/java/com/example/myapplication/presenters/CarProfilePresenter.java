package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
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

    private String currentCarNumber;

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

    public void getCarData() {
        getViewState().updateCarsData(dataGetter.getCars());
    }

    public void deleteCar(String carNumber) {
        disposable.add(dataGetter.deleteCar(carNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(car -> {
                }, throwable -> {
                })
        );
    }

    public void updateCar(String carNumber) {
        dataGetter.updateCar(currentCarNumber, carNumber.toUpperCase())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> System.out.println(),//Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void setCurrentCarNumber(String currentCarNumber) {
        this.currentCarNumber = currentCarNumber;
    }

    public void dispose() {
        disposable.dispose();
    }
}
