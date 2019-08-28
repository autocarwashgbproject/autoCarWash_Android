package com.example.myapplication.di;

import com.example.myapplication.presenters.RegisterPresenter;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(RegisterPresenter registerPresenter);
}
