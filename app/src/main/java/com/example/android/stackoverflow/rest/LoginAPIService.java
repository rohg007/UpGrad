package com.example.android.stackoverflow.rest;

import com.example.android.stackoverflow.Models.Login;
import com.example.android.stackoverflow.Models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginAPIService {
    @POST("login")
    Call<Login> login(@Body User user);

    @GET("access_token")
    Call<ResponseBody> getToken(@Header("Authorization") String token);
}
