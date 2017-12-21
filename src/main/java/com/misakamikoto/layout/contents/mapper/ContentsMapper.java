package com.misakamikoto.layout.contents.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.misakamikoto.layout.contents.model.ContentsVO;

/**
 * Created by Misaka on 2016-03-16.
 */
@Repository
public interface ContentsMapper {

    /**
     * Gets contents list.
     *
     * @param categoryCode the category code
     * @return the contents list
     */
    List<ContentsVO> getContentsList(int categoryCode);

    /**
     * Gets newest content.
     *
     * @return the newest content
     */
    ContentsVO getNewestContent();

    /**
     * Gets content.
     *
     * @param contentId the content id
     * @return the content
     */
    ContentsVO getContent(int contentId);

    /**
     * Add content.
     *
     * @param contentsVO the contents vo
     */
    void addContent(ContentsVO contentsVO);
}
