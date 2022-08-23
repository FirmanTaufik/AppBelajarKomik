package com.app.appbelajarkomik;

public class ListTrendingModel {
    private String judul, gambar, chapter, link;

    public ListTrendingModel(String judul, String gambar, String chapter, String link) {
        this.judul = judul;
        this.gambar = gambar;
        this.chapter = chapter;
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

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
