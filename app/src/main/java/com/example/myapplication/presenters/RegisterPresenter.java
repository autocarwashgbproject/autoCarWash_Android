package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.ApiRequests;
import com.example.myapplication.model.parsingJson.RegTel;
import com.example.myapplication.utils.Utils;
import com.example.myapplication.views.RegisterIF;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Const.CODE_ERROR;
import static com.example.myapplication.Const.PHONE_ERROR;
import static com.example.myapplication.Const.POLICY_ERROR;

@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterIF> {

    @Inject
    public ApiRequests api;

    public RegisterPresenter() {

    }

    public void register(String code, boolean checked) {

        // парсить код и высылать как инт на проверку

        if (!checked) {
            getViewState().showErrorMessage(POLICY_ERROR);
        } else if (code.length() < 4) {
            getViewState().showErrorMessage(CODE_ERROR);
        } else {
            getViewState().loadMain();
        }
    }

    public void getSmsCode(String phone) {
        if (!Utils.isValidMobile(phone)) {
            getViewState().showErrorMessage(PHONE_ERROR);
            return;
        }
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        Call<RegTel> call = api.regTel(Long.parseLong(phone));
        call.enqueue(new Callback<RegTel>() {
            @Override
            public void onResponse(Call<RegTel> call, Response<RegTel> response) {
                if (response.body() != null) {
                    System.out.println("response " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<RegTel> call, Throwable t) {
                System.out.println("failure - " + t);
            }
        });


    }
}
