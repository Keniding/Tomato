package com.keniding.tomato;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.databinding.ActivityRegisterBinding;
import com.keniding.tomato.databinding.ActivitySelectBinding;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySelectBinding binding = ActivitySelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String text = "Dipier";

        binding.setTitleText("Bienvenido");
        binding.setSubtitleText("Henry");
        binding.setTitleTextR(text);

        String restaurantName = "Chifa Fu Ga";
        binding.letterIcon.setText(restaurantName.substring(0, 1));

        binding.itemTittle.letterIcon.setText(text.substring(0, 1));

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
