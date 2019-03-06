package com.example.think.fruitlog.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class News {
    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("newslist")
    public List<NewsItem> newsList ;
}
