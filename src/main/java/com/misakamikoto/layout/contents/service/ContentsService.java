package com.misakamikoto.layout.contents.service;

import com.misakamikoto.layout.contents.mapper.ContentsMapper;
import com.misakamikoto.layout.contents.model.ContentsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    public ContentsVO getNewestContent() {
        return this.contentsMapper.getNewestContent();
    }

    public ContentsVO getContent(int contentId) {
        return this.contentsMapper.getContent(contentId);
    }

    public void addContent(String categoryCode, String title, String description, String videoId) {
        ContentsVO contentsVO = new ContentsVO();
        contentsVO.setCategoryCode(categoryCode);
        contentsVO.setSubject(title);
        contentsVO.setContent(description);
        contentsVO.setVideoId(videoId);

        this.contentsMapper.addContent(contentsVO);

    }
}
