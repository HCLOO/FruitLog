package com.example.think.fruitlog.model;

public class PraiseLog {
    float starNum;
    String time;
    String objectId;

    public PraiseLog(float starNum, String time,String objectId) {
        this.starNum = starNum;
        this.time = time;
        this.objectId=objectId;
    }

    public float getStarNum() {
        return starNum;
    }

    public String getTime() {
        return time;
    }

    public String getObjectId() {
        return objectId;
    }
}
