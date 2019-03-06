package com.example.think.fruitlog.bmobmodel;

import cn.bmob.v3.BmobObject;

public class PraiseLog extends BmobObject {
    String userName;
    float starNum;
    String time;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getStarNum() {
        return starNum;
    }

    public void setStarNum(float starNum) {
        this.starNum = starNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
