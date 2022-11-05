package com.app.appbelajarkomik.model;

public class ListMoreLatest {
    String judul, gambar, chapter, menit, link;

    public ListMoreLatest(String judul, String gambar, String chapter, String menit, String link) {
        this.judul = judul;
        this.gambar = gambar;
        this.chapter = chapter;
        this.menit = menit;
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

    public String getMenit() {
        return menit;
    }

    public void setMenit(String menit) {
        this.menit = menit;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
