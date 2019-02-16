package com.toqa.raseediads.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad implements Comparable<Ad>, Parcelable {

    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("order")
    @Expose
    private int order;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(Ad ad) {
        return (this.order - ad.order);
    }

    public Ad(Parcel in){
        this.picture = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.order = in.readInt();

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.picture);
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeInt(this.order);
    }
}
