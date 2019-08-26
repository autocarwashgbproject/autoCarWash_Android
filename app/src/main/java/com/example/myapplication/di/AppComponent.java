package com.example.myapplication.di;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
}
