package com.app.appbelajarkomik.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListChapterModel implements Parcelable {
    private String chapter, link;

    public ListChapterModel(String chapter, String link) {
        this.chapter = chapter;
        this.link = link;
    }

    protected ListChapterModel(Parcel in) {
        chapter = in.readString();
        link = in.readString();
    }

    public static final Creator<ListChapterModel> CREATOR = new Creator<ListChapterModel>() {
        @Override
        public ListChapterModel createFromParcel(Parcel in) {
            return new ListChapterModel(in);
        }

        @Override
        public ListChapterModel[] newArray(int size) {
            return new ListChapterModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(chapter);
        parcel.writeString(link);
    }
}
