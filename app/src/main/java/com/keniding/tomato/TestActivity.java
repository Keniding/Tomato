package com.keniding.tomato;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.keniding.tomato.test.model.ApiResponse;
import com.keniding.tomato.test.service.ApiService;
import com.keniding.tomato.test.service.RetrofitClient;
import com.keniding.tomato.test.util.CryptoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private ApiService apiService;
    private static final String CRYPTO_KEY = "ff551a94jdifbuiy345i3btjkhr45812345fbjdsfyjyf45634sdfsdf";
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiService = RetrofitClient.getClient().create(ApiService.class);

        logKeyInfo();
        obtenerTransaccion();
    }

    private void obtenerTransaccion() {
        String numeroDocumento = "12345678";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("numero_documento", numeroDocumento);
        } catch (JSONException e) {
            Log.e(TAG, "Error al crear JSON: " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString());

        Call<ApiResponse> call = apiService.obtenerTransaccion(body);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null) {
                        String dataCifrada = apiResponse.getData();
                        Log.d(TAG, "Datos cifrados recibidos: " + dataCifrada);
                        try {
                            String dataDescifrada = CryptoUtils.decrypt(dataCifrada, CRYPTO_KEY);
                            Log.d(TAG, "Transacción obtenida (descifrada): " + dataDescifrada);
                            Toast.makeText(TestActivity.this, "Transacción obtenida y descifrada", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error al descifrar: " + e.getMessage(), e);
                            Toast.makeText(TestActivity.this, "Error al descifrar datos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e(TAG, "Respuesta vacía o nula");
                        Toast.makeText(TestActivity.this, "Respuesta vacía o nula", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Error al obtener transacción. Código: " + response.code());
                    Toast.makeText(TestActivity.this, "Error al obtener transacción", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Fallo en la red: " + t.getMessage(), t);
                Toast.makeText(TestActivity.this, "Fallo en la red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logKeyInfo() {
        Log.d(TAG, "Longitud de la clave codificada: " + CRYPTO_KEY.length());
        byte[] decodedKey = Base64.decode(CRYPTO_KEY, Base64.DEFAULT);
        Log.d(TAG, "Longitud de la clave decodificada: " + decodedKey.length);
    }
}
