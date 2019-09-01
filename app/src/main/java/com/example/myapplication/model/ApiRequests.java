package com.example.myapplication.model;

import com.example.myapplication.model.parsingJson.ApiClient;
import com.example.myapplication.model.parsingJson.RegClient;
import com.example.myapplication.model.parsingJson.RegTel;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiRequests {

    /**
     * @param phone - String - 10 numbers
     *              function getting code from sms (test), for next /client/register
     * @return RegTel.smsForTests - otp code in next request
     */
    @FormUrlEncoded
    @POST("clients/get_sms/")
    Single<RegTel> regTel(@Field("phone") String phone);

    /**
     *
     * @param phone - String - 10 numbers
     * @param otp - String - 4 numbers - code from sms
     * function response api key for next actions
     * @return RegClient.id - api key for next actions
     */
    @FormUrlEncoded
    @POST("clients/register/")
    Single<RegClient> regUser(@Field("phone") String phone, @Field("otp") String otp);

    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function response client info
     * @return ApiClient
     */
    @GET("clients/{id}/")
    Single<ApiClient> getClient(@Path("id") String id, @Header("Authorization") String token);


    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function update client info
     *              if you will add a phone, that DB contents, you will recieve error in arg phone:
     *              "user с таким phone уже существует."
     * @return ApiClient
     */
    @PUT("clients/{id}/")
    Single<ApiClient> updateClient(@Path("id") String id, @Header("Authorization") String token, @Body ApiClient apiClient);

    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function delete client and current token
     * @return ApiClient with args:
     * ok
     * id
     * description
     */
    @DELETE("clients/{id}/")
    Single<ApiClient> deleteClient(@Path("id") String id, @Header("Authorization") String token);

    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function delete current token
     * @return ApiClient with args:
     * ok
     * id
     * description
     */
    @DELETE("clients/{id}/logout/")
    Single<ApiClient> logoutClient(@Path("id") String id, @Header("Authorization") String token);
}
