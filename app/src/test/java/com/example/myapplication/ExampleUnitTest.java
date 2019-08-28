package com.example.myapplication;

import com.example.myapplication.model.ApiRequests;
import com.example.myapplication.model.parsingJson.RegTel;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void regTelTest() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://185.17.121.228/api/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiRequests api = retrofit.create(ApiRequests.class);

        System.out.println("start request");

        Call<RegTel> call = api.regTel(9998887766L);
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