package com.example.think.fruitlog.model;

public class Fruit {
    int imageId;
    String name;
    String detail;
    String objectId;

    public Fruit(int imageId,String name,String detail) {
        this.imageId=imageId;
        this.name=name;
        this.detail=detail;
    }

    public Fruit(int imageId, String name, String detail, String objectId) {
        this.imageId = imageId;
        this.name = name;
        this.detail = detail;
        this.objectId = objectId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getObjectId() {
        return objectId;
    }
}
