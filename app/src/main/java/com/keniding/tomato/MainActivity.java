package com.keniding.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.keniding.tomato.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

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

            if (TextUtils.isEmpty(mainBinding.codigo.getText())) {
                mainBinding.tilCodigo.setError("Este campo es obligatorio");
                isValid = false;
            } else {
                mainBinding.tilCodigo.setError(null);
            }

            if (TextUtils.isEmpty(mainBinding.password.getText())) {
                mainBinding.tilPassword.setError("Este campo es obligatorio");
                isValid = false;
            } else {
                mainBinding.tilPassword.setError(null);
            }

            if (isValid) {
                mainBinding.codigo.setText("");
                mainBinding.password.setText("");

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        mainBinding.registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        mainBinding.restaurant.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });

        /*
        mainBinding.restaurant.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelectActivity.class);
            startActivity(intent);
            finish();
        });
*/

        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
