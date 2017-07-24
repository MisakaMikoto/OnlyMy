package com.misakamikoto.layout.title.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misakamikoto.layout.title.mapper.TitleMapper;
import com.misakamikoto.layout.title.model.TitleVO;

/**
 * Created by Misaka on 2016-03-16.
 */
@Service
public class TitleService {
    /**
     * The Title mapper.
     */
    TitleMapper titleMapper;

    /**
     * Gets name.
     *
     * @return the name
     */
    public TitleVO getName() {
        return this.titleMapper.getName();
    }
}
