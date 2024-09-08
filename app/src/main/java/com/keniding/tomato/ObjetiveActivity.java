package com.keniding.tomato;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.databinding.ActivityObjetiveBinding;

public class ObjetiveActivity extends AppCompatActivity {

    ActivityObjetiveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityObjetiveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register.setOnClickListener(v -> {
            binding.register.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            binding.registerDescription.setTextColor(getResources().getColor(R.color.white));
            binding.registerTitle.setTextColor(getResources().getColor(R.color.white));
            binding.invitado.setBackgroundColor(getResources().getColor(R.color.white));

            Intent intent = new Intent(ObjetiveActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        binding.invitado.setOnClickListener(v -> {
            binding.invitado.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            binding.invitadoDescription.setTextColor(getResources().getColor(R.color.white));
            binding.invitadoTitle.setTextColor(getResources().getColor(R.color.white));
            binding.register.setBackgroundColor(getResources().getColor(R.color.white));

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
