package com.example.lab2_20203554;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lab2_20203554.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String internet;
        if (tengoInternet()){
            internet = "¡Sí cuentas con internet! :D";
        }else {
            internet = "Error en la conexión :c";
        }
        Snackbar.make(binding.getRoot(), internet, Snackbar.LENGTH_SHORT)
                        .show();

        binding.button2.setOnClickListener(view -> {
            Intent intent = new Intent( this , LoginActivity.class);
            startActivity(intent);
        });
    }
    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test", "Internet: " + tieneInternet);

        return tieneInternet;
    }
}