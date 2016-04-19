package com.ryuha.blog.title.service;

import com.ryuha.blog.title.mapper.TitleMapper;
import com.ryuha.blog.title.model.TitleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Misaka on 2016-03-16.
 */
@Service(value = "titleService")
public class TitleService {
    @Resource(name = "titleMapper")
    private TitleMapper titleMapper;

    public TitleVO getName() {
        return this.titleMapper.getName();
    }
}
