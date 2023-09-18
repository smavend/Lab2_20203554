package com.example.lab2_20203554.services;

import com.example.lab2_20203554.entity.Profile;
import com.example.lab2_20203554.entity.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CredentialsService {
    @GET("/api")
    Call<Results> getResults();
}
