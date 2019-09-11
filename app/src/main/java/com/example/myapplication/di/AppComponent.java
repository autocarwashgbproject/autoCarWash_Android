package com.example.myapplication.di;

import com.example.myapplication.presenters.ProfilePresenter;
import com.example.myapplication.presenters.RegisterPresenter;
import com.example.myapplication.presenters.WelcomePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(RegisterPresenter registerPresenter);

    void inject(ProfilePresenter profilePresenter);

    void inject(WelcomePresenter welcomePresenter);
}
