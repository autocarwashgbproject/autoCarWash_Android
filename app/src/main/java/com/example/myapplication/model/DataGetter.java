package com.example.myapplication.model;

import android.widget.Toast;

import com.example.myapplication.App;
import com.example.myapplication.model.parsingJson.ApiClient;
import com.example.myapplication.model.parsingJson.RegClient;
import com.example.myapplication.model.parsingJson.RegTel;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
public class DataGetter {

    private static final String TOKEN_PREF = "Token ";
    private ApiRequests api;
    private String sessionToken;
    private String clientId;
    private String sms;
    private ApiClient currentClient;

    public DataGetter(ApiRequests api) {
        this.api = api;
    }

    public ApiClient getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(ApiClient currentClient) {
        this.currentClient = currentClient;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Single<RegClient> getToken(String phone) {
        Toast.makeText(App.getInstance(), "start request", Toast.LENGTH_SHORT).show();
        System.out.println("start request");

        return api.regUser(phone, sms)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                    sessionToken = response.getToken();
                    clientId = response.getIdClient();
                    return response;
                });
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
}
