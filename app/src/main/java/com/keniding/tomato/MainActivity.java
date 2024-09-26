package com.keniding.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.api.UserApiService;
import com.keniding.tomato.config.AuthManager;
import com.keniding.tomato.config.PreferenceManager;
import com.keniding.tomato.config.RetrofitClient;
import com.keniding.tomato.databinding.ActivityMainBinding;
import com.keniding.tomato.model.LoginResponse;
import com.keniding.tomato.model.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    UserApiService userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Retrofit retrofit = RetrofitClient.getClient();
        userApiService = retrofit.create(UserApiService.class);

        mainBinding.setTitleText("Tomato");
        mainBinding.setSubtitleText("Welcome");

        mainBinding.codigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainBinding.codigo.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mainBinding.password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainBinding.password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mainBinding.loginButton.setOnClickListener(v -> {
            boolean isValid = true;

            String username = Objects.requireNonNull(mainBinding.codigo.getText()).toString();
            String password = Objects.requireNonNull(mainBinding.password.getText()).toString();

            if (TextUtils.isEmpty(username)) {
                mainBinding.tilCodigo.setError("Este campo es obligatorio");
                isValid = false;
            } else {
                mainBinding.tilCodigo.setError(null);
            }

            if (TextUtils.isEmpty(password)) {
                mainBinding.tilPassword.setError("Este campo es obligatorio");
                isValid = false;
            } else {
                mainBinding.tilPassword.setError(null);
            }

            if (isValid) {
                User user = new User(username, password);
                login(user);
            }
        });

        mainBinding.registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        mainBinding.restaurant.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void login(User user) {
        Call<LoginResponse> call = userApiService.login(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String authToken = response.body().getToken();

                    AuthManager.setAuthToken(authToken);

                    PreferenceManager preferenceManager = new PreferenceManager(MainActivity.this);
                    preferenceManager.saveUsername(user.getUsername());

                    Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error de inicio de sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
