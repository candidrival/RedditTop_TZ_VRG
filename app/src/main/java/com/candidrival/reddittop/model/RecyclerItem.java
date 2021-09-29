package com.candidrival.reddittop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecyclerItem {

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("num_comments")
    @Expose
    private String numComments;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("created_utc")
    @Expose
    private long createdUtc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNumComments() {
        return numComments;
    }

    public void setNumComments(String numComments) {
        this.numComments = numComments;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(long createdUtc) {
        this.createdUtc = createdUtc;
    }
}
