package com.app.appbelajarkomik.model;

public class BookmarkModel {
    String userId, link, image, judul;

    public BookmarkModel() {
    }

    public BookmarkModel(String link, String image, String judul) {
        this.link = link;
        this.image = image;
        this.judul = judul;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
