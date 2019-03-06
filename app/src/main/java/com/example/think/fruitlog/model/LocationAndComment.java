package com.example.think.fruitlog.model;

public class LocationAndComment {
    String userName;
    String city;
    String storeName;
    String comment;
    String time;

    public LocationAndComment(String userName, String city, String storeName, String comment, String time) {
        this.userName = userName;
        this.city = city;
        this.storeName = storeName;
        this.comment = comment;
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
