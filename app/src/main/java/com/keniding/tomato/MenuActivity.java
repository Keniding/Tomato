package com.keniding.tomato;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.config.PreferenceManager;
import com.keniding.tomato.databinding.ActivityMainBinding;
import com.keniding.tomato.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding menuActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuActivity = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(menuActivity.getRoot());

        ActivityMenuBinding binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PreferenceManager preferenceManager = new PreferenceManager(MenuActivity.this);
        String username = preferenceManager.getUsername();

        binding.setTitleText("Welcome");
        if (username != null) {
            binding.setSubtitleText(username.toUpperCase());
        } else {
            binding.setSubtitleText("Tomato User");
        }

        binding.category.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}
