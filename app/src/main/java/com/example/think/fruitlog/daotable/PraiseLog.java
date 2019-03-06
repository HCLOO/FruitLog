package com.example.think.fruitlog.daotable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PraiseLog {
    @Id
    private Long id;
    private float starNum;
    private String time;
    @Generated(hash = 424590232)
    public PraiseLog(Long id, float starNum, String time) {
        this.id = id;
        this.starNum = starNum;
        this.time = time;
    }
    @Generated(hash = 972827333)
    public PraiseLog() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public float getStarNum() {
        return this.starNum;
    }
    public void setStarNum(float starNum) {
        this.starNum = starNum;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
