package com.keniding.tomato.test.service;

import com.keniding.tomato.test.model.ApiResponse;
import com.keniding.tomato.test.model.Transaccion;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/transaccion/insertar")
    Call<ApiResponse> insertarTransaccion(@Body Transaccion transaccion);

    /*
    @POST("api/transaccion/obtener")
    Call<ApiResponse> obtenerTransaccion(@Body Map<String, String> body);

     */

    @POST("api/transaccion/obtener")
    Call<ApiResponse> obtenerTransaccion(@Body RequestBody body);


}
