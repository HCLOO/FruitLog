package com.example.think.fruitlog.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    @SerializedName("ctime")
    public String time;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("picUrl")
    public String picUrl;

    @SerializedName("url")
    public String url;
}
