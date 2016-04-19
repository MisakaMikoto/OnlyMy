package com.ryuha.blog.title.mapper;

import com.ryuha.blog.title.model.TitleVO;
import org.springframework.stereotype.Repository;

/**
 * Created by Misaka on 2016-03-16.
 */

@Repository(value = "titleMapper")
public interface TitleMapper {
    TitleVO getName();
}
