package com.misakamikoto.layout.category.model;

import lombok.Data;

/**
 * Created by Misaka on 2016-03-08.
 */
@Data
public class CategoryVO {
    private String id;
    private Integer order;
    private Integer deleted;
    private String name;
    private Integer count;
}
