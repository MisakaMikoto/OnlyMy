package com.misakamikoto.layout.contents.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.misakamikoto.layout.contents.model.ContentsVO;

/**
 * The interface Contents mapper.
 */
@Repository
public interface ContentsMapper {
    /**
     * Gets newest content.
     *
     * @return the newest content
     */
    ContentsVO getNewestContent();

    /**
     * Gets contents list.
     *
     * @param codeId the code id
     * @return the contents list
     */
    List<ContentsVO> getContentsList(int codeId);

    /**
     * Add picture.
     *
     * @param contentsVO the contents vo
     */
    void addPicture(ContentsVO contentsVO);
}
