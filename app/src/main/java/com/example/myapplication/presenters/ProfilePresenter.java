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

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileIF> {

    @Inject
    public DataGetter dataGetter;

    @Inject
    public RoomCache roomCache;

    private CompositeDisposable disposable;

    public ProfilePresenter() {
        disposable = new CompositeDisposable();
    }

    public void getClientFromApi() {
        disposable.add(dataGetter.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            getViewState().updateData(result);
                            Toast.makeText(App.getInstance(), result.toString(), Toast.LENGTH_SHORT).show();
                        },
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show()));
    }

    public void getCarData() {
        getViewState().updateCarsData(dataGetter.getCars());
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
