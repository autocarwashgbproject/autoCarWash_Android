package com.example.myapplication.presenters;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.model.parsingJson.ApiClient;
import com.example.myapplication.views.ProfileIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileIF> {

    @Inject
    public DataGetter dataGetter;


    public void getClientFromApi() {

        dataGetter.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show();
                        },
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void updateClient() {
        final ApiClient client = dataGetter.getCurrentClient();
        client.setName("Alex");
        client.setSurname("Belyakov");
        client.setPatronymic("Olegovich");
        client.setEmail("aaa@aa.aa");
        client.setPhone("9855554229");
        dataGetter.updateProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void deleteClient() {
        dataGetter.deleteProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void logout() {
        dataGetter.logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void createCar(String carNumber) {
        dataGetter.createCar(carNumber.toUpperCase())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void updateCar(String carNumber) {
        dataGetter.updateCar(carNumber.toUpperCase(), carNumber.toUpperCase())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }

    public void deleteCar(String carNumber) {
        dataGetter.deleteCar(carNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show());
    }
}
