package com.misakamikoto.layout.category.model;

import java.io.Serializable;

/**
 * Created by Misaka on 2016-03-08.
 */
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Integer order;
    private Integer deleted;
    private String name;

    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
