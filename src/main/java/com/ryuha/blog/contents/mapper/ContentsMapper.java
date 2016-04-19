package com.ryuha.blog.contents.mapper;

import com.ryuha.blog.contents.model.ContentsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Misaka on 2016-03-16.
 */
@Repository(value = "contentsMapper")
public interface ContentsMapper {
    List<ContentsVO> getContentsList(String categoryCode);
    ContentsVO getNewestContent();
    ContentsVO getContent(int contentId);
}
