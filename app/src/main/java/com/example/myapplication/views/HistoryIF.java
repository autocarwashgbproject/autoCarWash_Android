package com.example.myapplication.views;

import com.arellomobile.mvp.MvpView;
import com.example.myapplication.model.cache.Wash;

import java.util.List;

public interface HistoryIF extends MvpView {

    void updateList(List<Wash> washes);
}
