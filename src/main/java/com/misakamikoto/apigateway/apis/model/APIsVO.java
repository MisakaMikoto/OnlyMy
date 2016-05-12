package com.misakamikoto.apigateway.apis.model;

import java.io.Serializable;

/**
 * Created by Misaka on 2016-05-12.
 */
public class APIsVO implements Serializable {
    String name;
    String slug;
    String targetUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
