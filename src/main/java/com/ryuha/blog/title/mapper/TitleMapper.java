package com.ryuha.blog.title.mapper;

import org.springframework.stereotype.Repository;

/**
 * Created by Misaka on 2016-03-16.
 */

@Repository(value = "titleMapper")
public interface TitleMapper {
    String getName();
}
