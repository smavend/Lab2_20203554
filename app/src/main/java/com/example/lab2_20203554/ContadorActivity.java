package com.example.lab2_20203554;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.example.lab2_20203554.databinding.ActivityContadorBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;

public class ContadorActivity extends AppCompatActivity {
    ActivityContadorBinding binding;
    int contador = 104;
    int lapso = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Snackbar.make(binding.getRoot(), "Vista contador: presiona el botón.", Snackbar.LENGTH_SHORT)
                .show();

        ApplicationThreads application = (ApplicationThreads) getApplication();
        ExecutorService executorService = application.executorService;

        ContadorViewModel contadorViewModel = new ViewModelProvider(ContadorActivity.this).get(ContadorViewModel.class);

        contadorViewModel.getContador().observe(this, contador -> {
            binding.cuenta.setText(String.valueOf(contador));
        });

        binding.iniciar.setOnClickListener(view -> {
            if (lapso>1) {
                lapso = (int) ((lapso / 2) + 0.5);
            }
            executorService.execute(() -> {
                while(true) {
                    if (contador==227){
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(500);
                        Snackbar.make(binding.getRoot(), "El contador se reiniciará en 10 segundos", Snackbar.LENGTH_SHORT)
                                .show();
                        try {
                            Thread.sleep(10000);
                            contador=104;
                            lapso=10000;
                            contadorViewModel.getContador().postValue(contador);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    contadorViewModel.getContador().postValue(contador++); // o1
                    Log.d("msg-test", "i: " +contador);
                    try {
                        Thread.sleep(lapso);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
    }
}