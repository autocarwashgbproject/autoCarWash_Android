package com.example.myapplication.presenters;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.model.cache.RoomCache;
import com.example.myapplication.model.cache.Wash;
import com.example.myapplication.views.ProfileIF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileIF> {

    @Inject
    public DataGetter dataGetter;

    @Inject
    public RoomCache roomCache;


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

    public void addRandomWash() {
        final Wash wash1 = new Wash("fff", true, new Date().getTime() - 50000, 1, 1, 1);
        final Wash wash2 = new Wash("fsss", true, new Date().getTime() - 40000, 2, 2, 2);
        final Wash wash3 = new Wash("sss", false, new Date().getTime() - 30000, 3, 3, 3);
        final Wash wash4 = new Wash("ccc", true, new Date().getTime() - 20000, 4, 4, 14);
        final Wash wash5 = new Wash("fttt", true, new Date().getTime(), 5, 51, 15);

        roomCache.addWashes(new ArrayList<>(Arrays.asList(wash1, wash2, wash3, wash4, wash5)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Toast.makeText(App.getInstance(), "success", Toast.LENGTH_SHORT).show());
    }

    public void getWashes() {
        roomCache.getWashes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (Wash wash : result) {
                        Toast.makeText(App.getInstance(), wash.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
