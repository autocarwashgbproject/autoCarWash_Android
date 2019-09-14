package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.MenuIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class MenuPresenter extends MvpPresenter<MenuIF> {

    @Inject
    public DataGetter dataGetter;

    private CompositeDisposable disposable;

    public MenuPresenter() {
        disposable = new CompositeDisposable();
    }

    public void updateProfile() {
        disposable.add(dataGetter.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().updateProfile(result),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show()
                )
        );
    }
}
