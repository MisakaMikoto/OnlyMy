package com.misakamikoto.layout.title.mapper;

import com.misakamikoto.layout.title.model.TitleVO;
import org.springframework.stereotype.Repository;

/**
 * Created by Misaka on 2016-03-16.
 */
public interface TitleMapper {
    /**
     * Gets name.
     *
     * @return the name
     */
    TitleVO getName();
}
