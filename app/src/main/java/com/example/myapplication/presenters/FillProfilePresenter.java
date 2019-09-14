package com.example.myapplication.presenters;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.myapplication.App;
import com.example.myapplication.model.DataGetter;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.model.cache.RoomCache;
import com.example.myapplication.views.FillProfileIF;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class FillProfilePresenter extends MvpPresenter<FillProfileIF> {

    @Inject
    public DataGetter dataGetter;

    @Inject
    public RoomCache roomCache;

    private CompositeDisposable disposable;

    public FillProfilePresenter() {
        disposable = new CompositeDisposable();
    }

    public void getClientFromApi() {
        disposable.add(dataGetter.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            getViewState().updateData(result);
                        },
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show()));
    }

    public void fillProfileData(ApiClient apiClient) {

        final ApiClient client = dataGetter.getCurrentClient();
        client.setName(apiClient.getName());
        client.setSurname(apiClient.getSurname());
        client.setPatronymic(apiClient.getPatronymic());
        client.setBirthday(apiClient.getBirthday());
        client.setEmail(apiClient.getEmail());
        client.setPhone(apiClient.getPhone());

        disposable.add(dataGetter.updateProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> System.out.println(),
                        err -> Toast.makeText(App.getInstance(), err.toString(), Toast.LENGTH_SHORT).show()
                )
        );
    }

    public void deleteProfile() {
        disposable.add(dataGetter.deleteProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(client -> System.out.println(),
                        throwable -> Toast.makeText(App.getInstance(), throwable.toString(), Toast.LENGTH_SHORT).show())
        );
    }
}
