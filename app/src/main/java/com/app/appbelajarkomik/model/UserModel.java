package com.app.appbelajarkomik.model;

public class UserModel {
    String key, email, nama, password;

    public UserModel() {
    }

    public UserModel(String key, String email, String nama, String password) {
        this.key = key;
        this.email = email;
        this.nama = nama;
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
