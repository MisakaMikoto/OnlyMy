package com.misakamikoto.layout.contents.model;

import java.io.Serializable;

/**
 * Created by Misaka on 2016-03-16.
 */
public class ContentsVO implements Serializable{
    private int id;
    private String categoryCode;
    private String subject;
    private String content;
    private String videoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoId() {
        return videoId;
    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
