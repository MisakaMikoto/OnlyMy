package com.ryuha.blog.title.model;

import java.io.Serializable;

/**
 * Created by Misaka on 2016-03-16.
 */
public class TitleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
