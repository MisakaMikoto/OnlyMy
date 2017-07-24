package com.misakamikoto.layout.title.model;

import java.io.Serializable;

/**
 * Created by Misaka on 2016-03-16.
 */
public class TitleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
