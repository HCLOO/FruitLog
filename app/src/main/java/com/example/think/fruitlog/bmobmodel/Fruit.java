package com.example.think.fruitlog.bmobmodel;

import cn.bmob.v3.BmobObject;

public class Fruit extends BmobObject {
    private String userName;
    private String fruitName;
    private int fruitImageId;
    private String fruitDetail;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public void setFruitImageId(int fruitImageId) {
        this.fruitImageId = fruitImageId;
    }

    public void setFruitDetail(String fruitDetail) {
        this.fruitDetail = fruitDetail;
    }

    public String getUserName() {
        return userName;
    }

    public String getFruitName() {
        return fruitName;
    }

    public int getFruitImageId() {
        return fruitImageId;
    }

    public String getFruitDetail() {
        return fruitDetail;
    }
}
