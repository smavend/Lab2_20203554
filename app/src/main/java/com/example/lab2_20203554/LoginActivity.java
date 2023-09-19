package com.example.lab2_20203554;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_20203554.databinding.ActivityLoginBinding;
import com.example.lab2_20203554.entity.Profile;
import com.example.lab2_20203554.entity.Results;
import com.example.lab2_20203554.services.CredentialsService;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    CredentialsService credentialsService;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Snackbar.make(binding.getRoot(), "Vista de registro", Snackbar.LENGTH_SHORT)
                .show();

        intent = new Intent(this, MenuActivity.class);

        credentialsService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CredentialsService.class);
        fetchWebServiceData();
        binding.button.setOnClickListener(view -> {
            String nombre = binding.nombre.getEditText().getText().toString();
            String apellido = binding.apellido.getEditText().getText().toString();
            String correo = binding.correo.getEditText().getText().toString();
            String contrasena = binding.contrasena.getEditText().getText().toString();
            if (binding.checkBox.isChecked() && nombre != null && apellido != null && correo != null && contrasena != null && tengoInternet()){
                startActivity(intent);
            }
            else if(!tengoInternet()){
                Snackbar.make(binding.getRoot(), "No puedes continuar si no cuentas con internet.", Snackbar.LENGTH_SHORT)
                        .show();
            }else {
                Snackbar.make(binding.getRoot(), "Llene todos los campos y checkbox.", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

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

                        intent.putExtra("result", results);
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