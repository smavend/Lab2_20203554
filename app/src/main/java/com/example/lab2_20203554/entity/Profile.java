package com.example.lab2_20203554.entity;

import java.io.Serializable;

public class Profile implements Serializable {
    private Name name;
    private Login login;
    private String email;
    private Picture picture;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getCorreo() {
        return email;
    }

    public void setCorreo(String correo) {
        this.email = correo;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
