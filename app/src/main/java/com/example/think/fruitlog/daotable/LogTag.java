package com.example.think.fruitlog.daotable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LogTag {
    @Id
    private Long id;
    private String logTitle;
    private String logTime;
    private String logContent;
    @Generated(hash = 207509528)
    public LogTag(Long id, String logTitle, String logTime, String logContent) {
        this.id = id;
        this.logTitle = logTitle;
        this.logTime = logTime;
        this.logContent = logContent;
    }
    @Generated(hash = 2000216759)
    public LogTag() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogTitle() {
        return this.logTitle;
    }
    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }
    public String getLogTime() {
        return this.logTime;
    }
    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
    public String getLogContent() {
        return this.logContent;
    }
    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
