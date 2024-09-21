package com.keniding.tomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.keniding.tomato.adater.CategoryAdapter;
import com.keniding.tomato.api.CategoryApiService;
import com.keniding.tomato.config.RetrofitClient;
import com.keniding.tomato.model.Category;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private CategoryApiService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(new ArrayList<>());
        categoryRecyclerView.setAdapter(categoryAdapter);

        categoryService = RetrofitClient.getClient().create(CategoryApiService.class);

        loadCategories();
    }

    private void loadCategories() {
        Call<List<Category>> call = categoryService.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    if (categories.isEmpty()) {
                        Toast.makeText(CategoryActivity.this, "No se encontraron categorías", Toast.LENGTH_SHORT).show();
                    } else {
                        categoryAdapter.updateCategories(categories);
                        Toast.makeText(CategoryActivity.this, "Categorías cargadas con éxito", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CategoryActivity.this, "Error al cargar categorías: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}