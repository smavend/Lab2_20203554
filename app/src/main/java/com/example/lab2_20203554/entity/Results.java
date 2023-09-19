package com.example.lab2_20203554.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Results implements Serializable {
    private ArrayList<Profile> results;

    public ArrayList<Profile> getResults() {
        return results;
    }

    public void setResults(ArrayList<Profile> results) {
        this.results = results;
    }
}
