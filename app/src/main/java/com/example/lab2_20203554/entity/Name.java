package com.example.lab2_20203554.entity;

import java.io.Serializable;

public class Name implements Serializable {
    private String last;
    private String first;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
