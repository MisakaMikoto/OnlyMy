package com.ryuha.blog.contents.service;

import com.ryuha.blog.category.mapper.CategoryMapper;
import com.ryuha.blog.category.model.CategoryVO;
import com.ryuha.blog.contents.mapper.ContentsMapper;
import com.ryuha.blog.contents.model.ContentsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Misaka on 2016-03-16.
 */
@Service(value = "contentsService")
public class ContentsService {
    @Resource(name = "contentsMapper")
    private ContentsMapper contentsMapper;

    public List<ContentsVO> getContentsList(String categoryCode) {
        return this.contentsMapper.getContentsList(categoryCode);
    }

    public ContentsVO getNewestContents() {
        return this.contentsMapper.getNewestContents();
    }

    public ContentsVO getContent(int contentId) {
        return this.contentsMapper.getContent(contentId);
    }
}
