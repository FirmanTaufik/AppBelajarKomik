package com.app.appbelajarkomik;

public class ListChapterModel {
    private String chapter, link;

    public ListChapterModel(String chapter, String link) {
        this.chapter = chapter;
        this.link = link;
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
