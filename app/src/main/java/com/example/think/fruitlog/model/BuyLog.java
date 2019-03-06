package com.example.think.fruitlog.model;

public class BuyLog {
    int imageId;
    String name;
    String time;

    public BuyLog(int imageId,String name,String time) {
        this.imageId=imageId;
        this.name=name;
        this.time=time;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}

