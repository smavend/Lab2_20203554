package com.example.lab2_20203554;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.lab2_20203554.databinding.ActivityLoginBinding;
import com.example.lab2_20203554.entity.Profile;
import com.example.lab2_20203554.entity.Results;
import com.example.lab2_20203554.services.CredentialsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    CredentialsService credentialsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        credentialsService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CredentialsService.class);
        fetchWebServiceData();



    }
    public void fetchWebServiceData(){
        if(tengoInternet()){
            credentialsService.getResults().enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    if(response.isSuccessful()){

                        Results results = response.body();
                        Log.d("msg-test", results.getResults().get(0).getName().getFirst());
                        EditText nombre = binding.nombre.getEditText();
                        nombre.setText(results.getResults().get(0).getName().getFirst());
                        EditText apellido = binding.apellido.getEditText();
                        apellido.setText(results.getResults().get(0).getName().getLast());
                        EditText correo = binding.correo.getEditText();
                        correo.setText(results.getResults().get(0).getCorreo());
                        EditText contrasena = binding.contrasena.getEditText();
                        contrasena.setText(results.getResults().get(0).getLogin().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {

                }
            });
        }
    }
    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test", "Internet: " + tieneInternet);

        return tieneInternet;
    }
}