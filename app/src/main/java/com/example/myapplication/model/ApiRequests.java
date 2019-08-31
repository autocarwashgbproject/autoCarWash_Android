package com.example.myapplication.model;

import com.example.myapplication.model.parsingJson.ApiUser;
import com.example.myapplication.model.parsingJson.RegTel;
import com.example.myapplication.model.parsingJson.RegUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequests {
    @FormUrlEncoded
    @POST("clients/get_sms/")
    Call<RegTel> regTel(@Field("phone") String telNumber);

    @FormUrlEncoded
    @POST("clients/register/")
    Call<RegUser> regUser(@Field("phone") int phone, @Field("otp") int otp);

    @GET("clients/1/")
    Call<ApiUser> getClient();
}
