package com.example.myapplication.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.views.PaymentIF;

@InjectViewState
public class PaymentPresenter extends MvpPresenter<PaymentIF> {

    public PaymentPresenter() {
    }
}
