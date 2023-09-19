package com.example.lab2_20203554;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.lab2_20203554.databinding.ActivityMenuBinding;
import com.example.lab2_20203554.entity.Results;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        binding = ActivityMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        Results result = (Results) intent.getSerializableExtra("result");

        Snackbar.make(binding.getRoot(), "¡"+result.getResults().get(0).getName().getFirst()+", bienvenida al menú pricipal!", Snackbar.LENGTH_SHORT)
                .show();

        binding.fullname.setText(result.getResults().get(0).getName().getFirst().toString() + " " +result.getResults().get(0).getName().getLast().toString());
        binding.username.setText(result.getResults().get(0).getLogin().getUsername());
        ImageView imageView = binding.perfil;
        Picasso.with(this).load(result.getResults().get(0).getPicture().getLarge()).into(imageView);
    }
}