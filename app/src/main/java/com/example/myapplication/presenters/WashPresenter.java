package com.example.myapplication.presenters;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.WashIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class WashPresenter extends MvpPresenter<WashIF> {

    @Inject
    public DataGetter dataGetter;

    private CompositeDisposable disposable;


    public WashPresenter() {
        disposable = new CompositeDisposable();
    }

    public void getClientFromApi() {
        disposable.add(dataGetter.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            getViewState().updateClientData(result);
                            Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show();
                        },
                        err -> {
                            Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show();
                        })
        );

    }

    public void getCarData() {
        getViewState().updateCarsData(dataGetter.getCars());
    }

    public void dispose() {
        disposable.dispose();
    }
}
