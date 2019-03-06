package com.example.think.fruitlog.bmobmodel;

import cn.bmob.v3.BmobObject;

public class LocationAndComment extends BmobObject {
    String userName;
    String city;
    String storeName;
    String comment;
    String time;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public String getCity() {
        return city;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }
}
