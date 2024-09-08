package com.keniding.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.databinding.ActivityMainBinding;
import com.keniding.tomato.databinding.ActivityObjetiveBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.username.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.username.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.username.getText().toString().trim().isEmpty()) {
                    binding.username.setError("Nombre de usuario requerido");
                    return;
                }

                if (binding.password.getText().toString().trim().isEmpty()) {
                    binding.password.setError("Contraseña requerida");
                    return;
                }

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }

        });

        binding.restaurant.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ObjetiveActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
