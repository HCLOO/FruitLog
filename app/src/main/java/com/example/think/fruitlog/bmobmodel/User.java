package com.example.think.fruitlog.bmobmodel;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String sex;

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
