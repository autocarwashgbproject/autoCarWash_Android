package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.ApiRequests;
import com.example.myapplication.model.parsingJson.RegTel;
import com.example.myapplication.model.parsingJson.RegUser;
import com.example.myapplication.views.RegisterIF;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterIF> {

    @Inject
    public ApiRequests api;

    private String sms;

    public RegisterPresenter() {

    }

    public void register(String code, boolean checked) {

        // парсить код и высылать как инт на проверку

        /*if (!checked) {
            getViewState().showErrorMessage("Принять соглашение");
        } else if (code.length() < 4) {
            getViewState().showErrorMessage("Пожалуйста введите код подтверждения");
        } else {
            getViewState().loadMain();
        }*/
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        Call<RegUser> call = api.regUser(2147483647, Integer.parseInt(sms));
        call.enqueue(new Callback<RegUser>() {
            @Override
            public void onResponse(Call<RegUser> call, Response<RegUser> response) {
                if (response.body() != null) {
                    System.out.println("response " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<RegUser> call, Throwable t) {
                System.out.println("failure - " + t);
            }
        });
    }

    public void getSmsCode(String phone) {
        /*if (!Utils.isValidMobile(phone)) {
            getViewState().showErrorMessage("Проверьте номер телефона");
            return;
        }*/
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        Call<RegTel> call = api.regTel("2147483647"); //Long.parseLong(phone));
        call.enqueue(new Callback<RegTel>() {
            @Override
            public void onResponse(Call<RegTel> call, Response<RegTel> response) {
                if (response.body() != null) {
                    System.out.println("response " + response.body().toString());
                    sms = response.body().getSmsForTests();
                }
            }

            @Override
            public void onFailure(Call<RegTel> call, Throwable t) {
                System.out.println("failure - " + t);
            }
        });


    }
}
