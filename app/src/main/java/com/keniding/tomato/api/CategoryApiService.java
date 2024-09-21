package com.keniding.tomato.api;

import com.keniding.tomato.model.Category;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.UUID;

public interface CategoryApiService {
    @GET("category")
    Call<List<Category>> getAllCategories();

    @GET("category/{id}")
    Call<Category> getCategoryById(@Path("id") UUID id);

    @POST("category")
    Call<Category> createCategory(@Body Category category);

    @PUT("category/{id}")
    Call<Category> updateCategory(@Path("id") UUID id, @Body Category category);

    @DELETE("category/{id}")
    Call<Void> deleteCategory(@Path("id") UUID id);
}
