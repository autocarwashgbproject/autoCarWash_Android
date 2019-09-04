package com.example.myapplication.di;

import com.example.myapplication.presenters.ProfilePresenter;
import com.example.myapplication.presenters.RegisterPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(RegisterPresenter registerPresenter);

    void inject(ProfilePresenter profilePresenter);
}
