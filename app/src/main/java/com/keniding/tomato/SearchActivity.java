package com.keniding.tomato;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchActivity extends AppCompatActivity {

    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FloatingActionButton fabMain = findViewById(R.id.fab_main);
        FloatingActionButton fabOption1 = findViewById(R.id.fab_option1);
        FloatingActionButton fabOption2 = findViewById(R.id.fab_option2);

        fabMain.setOnClickListener(view -> {
            if (isFabOpen) {
                fabOption1.setVisibility(View.GONE);
                fabOption2.setVisibility(View.GONE);
                isFabOpen = false;
            } else {
                fabOption1.setVisibility(View.VISIBLE);
                fabOption2.setVisibility(View.VISIBLE);
                isFabOpen = true;
            }
        });

        fabOption1.setOnClickListener(view -> {

        });

        fabOption2.setOnClickListener(view -> {

        });
    }
}
