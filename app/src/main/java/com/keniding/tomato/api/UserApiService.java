package com.keniding.tomato.api;

import com.keniding.tomato.model.LoginResponse;
import com.keniding.tomato.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {

    @POST("login")
    Call<LoginResponse> login(@Body User user);

}
