package com.example.taufiq.mitchnotes.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created By Taufiq on 6/25/2019.
 * MitchNotes
 */

public class Notes implements Parcelable {

    private String title;
    private String content;
    private String timeStamp;

    public Notes(String title, String content, String timeStamp) {
        this.title = title;
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
    }


    protected Notes(Parcel in) {
        title = in.readString();
        content = in.readString();
        timeStamp = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(timeStamp);
    }
}
