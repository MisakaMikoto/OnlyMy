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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets category code.
     *
     * @return the category code
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets category code.
     *
     * @param categoryCode the category code
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets video id.
     *
     * @return the video id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * Sets video id.
     *
     * @param videoId the video id
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
