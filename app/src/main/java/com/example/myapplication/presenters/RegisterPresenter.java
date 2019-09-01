package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.RegisterIF;

import javax.inject.Inject;

@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterIF> {

    @Inject
    public DataGetter dataGetter;


    public RegisterPresenter() {

    }

    public void register(String code, boolean checked) {

        if (!checked) {
            getViewState().showErrorMessage("Принять соглашение");
        } else if (code.length() < 4) {
            getViewState().showErrorMessage("Пожалуйста введите код подтверждения");
        } else {
            Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
            System.out.println("start request");

            dataGetter.getToken("9855554229")
                    .subscribe(regClient -> {
                        System.out.println("response " + regClient.toString());
                        getViewState().loadMain();
                    });
        }

    }

    public void getSmsCode(String phone) {
        /*if (!Utils.isValidMobile(phone)) {
            getViewState().showErrorMessage("Проверьте номер телефона");
            return;
        }*/
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        dataGetter.getSmsCode("9855554229").subscribe(result -> {
            dataGetter.setSms(result.getSmsForTests());
            System.out.println(result.toString());
        });
    }
}
