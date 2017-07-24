package com.misakamikoto.layout.contents.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misakamikoto.layout.contents.mapper.ContentsMapper;
import com.misakamikoto.layout.contents.model.ContentsVO;

/**
 * Created by Misaka on 2016-03-16.
 */
@Service
public class ContentsService {
    /**
     * The Contents mapper.
     */
    @Autowired
    ContentsMapper contentsMapper;

    /**
     * Gets contents list.
     *
     * @param categoryCode the category code
     * @return the contents list
     */
    public List<ContentsVO> getContentsList(String categoryCode) {
        return this.contentsMapper.getContentsList(categoryCode);
    }

    /**
     * Gets newest content.
     *
     * @return the newest content
     */
    public ContentsVO getNewestContent() {
        return this.contentsMapper.getNewestContent();
    }


    /**
     * Gets content.
     *
     * @param contentId the content id
     * @return the content
     */
    public ContentsVO getContent(int contentId) {
        return this.contentsMapper.getContent(contentId);
    }


    /**
     * Add content.
     *
     * @param categoryCode the category code
     * @param title        the title
     * @param description  the description
     * @param videoId      the video id
     */
    public void addContent(String categoryCode, String title, String description, String videoId) {
        ContentsVO contentsVO = new ContentsVO();
        contentsVO.setCategoryCode(categoryCode);
        contentsVO.setSubject(title);
        contentsVO.setContent(description);
        contentsVO.setVideoId(videoId);

        this.contentsMapper.addContent(contentsVO);

    }
}
