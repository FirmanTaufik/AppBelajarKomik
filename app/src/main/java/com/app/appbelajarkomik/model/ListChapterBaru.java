package com.app.appbelajarkomik.model;

public class ListChapterBaru {
    String judul ,gambar,chapter,status,rating
            ,view,link;

    public ListChapterBaru(String judul, String gambar, String chapter, String status, String rating, String view, String link) {
        this.judul = judul;
        this.gambar = gambar;
        this.chapter = chapter;
        this.status = status;
        this.rating = rating;
        this.view = view;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
