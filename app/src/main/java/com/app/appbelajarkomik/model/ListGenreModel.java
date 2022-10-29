package com.app.appbelajarkomik.model;

public class ListGenreModel {
    String title, gambar, jenis, rating, link,warna;

    public ListGenreModel(String title, String gambar, String jenis, String rating, String link, String warna) {
        this.title = title;
        this.gambar = gambar;
        this.jenis = jenis;
        this.rating = rating;
        this.link = link;
        this.warna = warna;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
}
