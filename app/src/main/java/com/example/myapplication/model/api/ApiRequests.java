package com.example.myapplication.model.api;

import com.example.myapplication.model.api.parsingJson.ApiCar;
import com.example.myapplication.model.api.parsingJson.ApiClient;
import com.example.myapplication.model.api.parsingJson.RegTel;
import com.example.myapplication.model.api.parsingJson.Registration;

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
    @POST("clients/sms/")
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
    Single<Registration> regUser(@Field("phone") String phone, @Field("otp") String otp);

    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function response client info
     * @return ApiClient
     */
    @GET("clients/{id_client}/")
    Single<ApiClient> getClient(@Path("id_client") String id, @Header("Authorization") String token);


    /**
     * @param id    - String - client id from response regUser. If you try get client
     *              with another id, it will returned error "forbidden" in detail
     * @param token - String - token from regUser.getToken()
     *              function update client info
     *              if you will add a phone, that DB contents, you will recieve error in arg phone:
     *              "user с таким phone уже существует."
     * @return ApiClient
     */
    @PUT("clients/{id_client}/")
    Single<ApiClient> updateClient(@Path("id_client") String id, @Header("Authorization") String token, @Body ApiClient apiClient);

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
    @DELETE("clients/{id_client}/")
    Single<ApiClient> deleteClient(@Path("id_client") String id, @Header("Authorization") String token);

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
    @DELETE("clients/{id_client}/logout/")
    Single<ApiClient> logoutClient(@Path("id_client") String id, @Header("Authorization") String token);

    /**
     * @param carNumber - String(9) - car number, format A999AA777
     * @param token     - String - token from regUser.getToken()
     *                  function create new car in DB
     * @return ApiCar
     */
    @FormUrlEncoded
    @POST("cars/")
    Single<ApiCar> createCar(@Field("reg_num") String carNumber, @Header("Authorization") String token);

    /**
     * @param id    - String - car id from List in ApiClient.
     * @param token - String - token from regUser.getToken()
     *              function response car info
     * @return ApiClient
     */
    @GET("cars/{id}/")
    Single<ApiCar> getCar(@Path("id") int id, @Header("Authorization") String token);


    /**
     * @param id    - String - car id from List in ApiClient.
     * @param token - String - token from regUser.getToken()
     *              function update carinfo
     *              if you will add a car number, that DB contents, you will recieve error in arg phone:
     *              "car с таким reg_num уже существует."
     * @return ApiClient
     */
    @PUT("cars/{id}/")
    Single<ApiCar> updateCar(@Path("id") int id, @Header("Authorization") String token, @Body ApiCar apiCar);

    /**
     * @param id    - String - car id from response regUser.
     * @param token - String - token from regUser.getToken()
     *              function delete car
     * @return ApiClient
     */
    @DELETE("cars/{id}/")
    Single<ApiCar> deleteCar(@Path("id") int id, @Header("Authorization") String token);
}
