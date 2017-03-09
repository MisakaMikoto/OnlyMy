package com.misakamikoto.layout.contents.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.misakamikoto.layout.contents.model.ContentsVO;

/**
 * Created by Misaka on 2016-03-16.
 */
@Repository
public interface ContentsMapper {
    List<ContentsVO> getContentsList(String categoryCode);
    ContentsVO getNewestContent();
    ContentsVO getContent(int contentId);
    void addContent(ContentsVO contentsVO);
}
