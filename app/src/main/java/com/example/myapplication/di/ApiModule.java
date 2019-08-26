package com.example.myapplication.di;

import com.example.myapplication.model.DataGetter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Named("testUrl")
    @Provides
    public String baseUrl() {
        return "http://185.17.121.228/api/v1/";
    }


    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    public Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    public Retrofit getRetrofitApi(OkHttpClient okHttpClient, Gson gson, @Named("testUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    public DataGetter getData() {
        return new DataGetter();
    }
}
