package com.example.think.fruitlog.model;

public class LogTag {
    String title;
    String time;
    String content;
    String objectId;

    public LogTag(String title, String time, String content,String objectId) {
        this.title = title;
        this.time = time;
        this.content=content;
        this.objectId=objectId;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getObjectId() {
        return objectId;
    }
}
