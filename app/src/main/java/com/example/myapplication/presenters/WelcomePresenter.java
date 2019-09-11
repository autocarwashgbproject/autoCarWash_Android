package com.example.myapplication.presenters;


import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.views.WelcomeIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import static com.example.myapplication.Const.CODE_ERROR;
import static com.example.myapplication.Const.POLICY_ERROR;

@InjectViewState
public class WelcomePresenter extends MvpPresenter<WelcomeIF> {

    @Inject
    public DataGetter dataGetter;

    private CompositeDisposable disposable;
    private String phoneNr;


    public WelcomePresenter() {
        disposable = new CompositeDisposable();
    }

    public void register(String code) {

        if (code.length() < 4) {
            getViewState().showErrorMessage(CODE_ERROR);
        } else {
            Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
            System.out.println("start request");

            disposable.add(dataGetter.getToken(phoneNr)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(regClient -> {
                                System.out.println("response " + regClient.toString());
                                getViewState().loadMain();
                            },
                            err -> {
                                Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show();
                            }));
        }

    }

    public void getSmsCode(String phone) {
        phoneNr = phone;
        /*if (!Utils.isValidMobile(phone)) {
            getViewState().showErrorMessage("Проверьте номер телефона");
            return;
        }*/
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        disposable.add(dataGetter.getSmsCode(phoneNr)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dataGetter.setSms(result.getSmsForTests());
                            getViewState().fillCodeFields(result.getSmsForTests());
                            System.out.println(result.toString());
                        },
                        err -> {
                            Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show();
                        }));
    }

    public void dispose() {
        disposable.dispose();
    }

}
