package com.example.myapplication.di;

import com.example.myapplication.presenters.CarProfilePresenter;
import com.example.myapplication.presenters.FillProfilePresenter;
import com.example.myapplication.presenters.HistoryPresenter;
import com.example.myapplication.presenters.MenuPresenter;
import com.example.myapplication.presenters.ProfilePresenter;
import com.example.myapplication.presenters.RegisterPresenter;
import com.example.myapplication.presenters.WashPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, CacheModule.class})
public interface AppComponent {

    void inject(RegisterPresenter registerPresenter);

    void inject(ProfilePresenter profilePresenter);

    void inject(WashPresenter washPresenter);

    void inject(FillProfilePresenter fillProfilePresenter);

    void inject(CarProfilePresenter carProfilePresenter);

    void inject(MenuPresenter presenter);

    void inject(HistoryPresenter historyPresenter);
}
