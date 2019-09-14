package com.example.myapplication.model;

import com.example.myapplication.model.api.ApiRequests;
import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.model.api.parsingJson.RegTel;
import com.example.myapplication.model.api.parsingJson.Registration;
import com.example.myapplication.model.cache.RoomCache;
import com.example.myapplication.model.cache.Wash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class DataGetter {

    private static final String TOKEN_PREF = "Token ";
    private ApiRequests api;
    private RoomCache cache;
    private String sessionToken;
    private String clientId;
    private String sms;
    private ApiClient currentClient;
    private Map<Integer, ApiCar> cars = new HashMap<>();

    public DataGetter(ApiRequests api, RoomCache cache) {

        this.api = api;
        this.cache = cache;

    }

    public ApiClient getCurrentClient() {
        return currentClient;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public void setApi(ApiRequests api) {
        this.api = api;
    }

    public Single<Registration> getToken(String phone) {

        return api.regUser(phone, sms)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                    sessionToken = response.getToken();
                    clientId = String.valueOf(response.getId());
                    fillCars(response.getCarsId());
                    return response;
                });
    }

    private void fillCars(Set<Integer> carsId) {
        cars.clear();
        for (int id : carsId) {
            api.getCar(id, TOKEN_PREF + sessionToken)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(res -> {
                        cars.put(res.getId(), res);
                        //Toast.makeText(App.getInstance().getBaseContext(), res.getRegNum(), Toast.LENGTH_SHORT).show();
                    });
        }
    }


    public Single<RegTel> getSmsCode(String phone) {
        return api.regTel(phone)
                .subscribeOn(Schedulers.newThread())
                .map(regUser -> {
                    sms = regUser.getSmsForTests();
                    return regUser;
                });
    }

    public Single<ApiClient> getProfile() {
        return api.getClient(clientId, TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    currentClient = result;
                    return result;
                });
    }

    public Single<ApiClient> updateProfile() {
        return api.updateClient(currentClient.getId(), TOKEN_PREF + sessionToken, currentClient)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    currentClient = result;
                    return result;
                });
    }

    public Single<ApiClient> deleteProfile() {
        return api.deleteClient(currentClient.getId(), TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    currentClient = result;
                    return result;
                });
    }

    public Single<ApiClient> logout() {
        return api.logoutClient(currentClient.getId(), TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    currentClient = result;
                    return result;
                });
    }

    public Single<ApiCar> createCar(String carNumber) {
        return api.createCar(carNumber, TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    cars.put(result.getId(), result);
                    return result;
                });
    }

    public Single<ApiCar> updateCar(String oldCarNumber, String carNumber) {
        for (Map.Entry<Integer, ApiCar> entry : cars.entrySet()) {
            if (entry.getValue().getRegNum().equals(oldCarNumber)) {
                ApiCar currentCar = entry.getValue();
                currentCar.setRegNum(carNumber);
                return api.updateCar(currentCar.getId(), TOKEN_PREF + sessionToken, currentCar)
                        .subscribeOn(Schedulers.newThread())
                        .map(result -> {
                            cars.remove(oldCarNumber);
                            cars.put(result.getId(), result);
                            return result;
                        });
            }
        }
        return null;

    }

    public Single<ApiCar> deleteCar(String carNumber) {
        ApiCar currentCar = cars.get(carNumber.toUpperCase());
        cars.remove(currentCar.getRegNum());
        return api.deleteCar(currentCar.getId(), TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread());
    }

    public Single<List<Wash>> getWashHistiry() {
        return api.getWashesHistory(clientId, TOKEN_PREF + sessionToken)
                .subscribeOn(Schedulers.newThread())
                .map(result -> {
                    if (!result.getWashes().isEmpty()) {
                        cache.addWashes(result.getWashes()).subscribe();
                    }
                    return result.getWashes();
                });
    }

    public Map<Integer, ApiCar> getCars() {
        return cars;
    }

    public void addWash() {
        String mCarNumber = "";
        for (Map.Entry<Integer, ApiCar> entry : cars.entrySet()) {
            mCarNumber = entry.getValue().getRegNum();
        }
        api.addWash(TOKEN_PREF + sessionToken, mCarNumber, true)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

}
