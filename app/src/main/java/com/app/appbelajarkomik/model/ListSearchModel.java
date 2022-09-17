package com.app.appbelajarkomik.model;

public class ListSearchModel {
    private String judul, gambar, rating, link;

    public ListSearchModel(String judul, String gambar, String rating, String link) {
        this.judul = judul;
        this.gambar = gambar;
        this.rating = rating;
        this.link = link;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
