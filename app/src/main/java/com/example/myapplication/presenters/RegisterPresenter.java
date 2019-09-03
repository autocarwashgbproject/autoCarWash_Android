package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.RegisterIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.example.myapplication.Const.CODE_ERROR;
import static com.example.myapplication.Const.PHONE_ERROR;
import static com.example.myapplication.Const.POLICY_ERROR;

@InjectViewState
public class RegisterPresenter extends MvpPresenter<RegisterIF> {

    @Inject
    public DataGetter dataGetter;


    public RegisterPresenter() {

    }

    public void register(String code, boolean checked) {

        if (!checked) {
            getViewState().showErrorMessage(POLICY_ERROR);
        } else if (code.length() < 4) {
            getViewState().showErrorMessage(CODE_ERROR);
        } else {
            Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
            System.out.println("start request");

            dataGetter.getToken("9855554229")
                    .subscribe(regClient -> {
                                System.out.println("response " + regClient.toString());
                                getViewState().loadMain();
                            },
                            err -> {
                                Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show();
                            });
        }

    }

    public void getSmsCode(String phone) {

        /*if (!Utils.isValidMobile(phone)) {
            getViewState().showErrorMessage("Проверьте номер телефона");
>>>>>>> feature/api-model
            return;
        }*/
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        dataGetter.getSmsCode("9855554229")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dataGetter.setSms(result.getSmsForTests());
                            System.out.println(result.toString());
                        },
                        err -> {
                            Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show();
                        });
    }
}
