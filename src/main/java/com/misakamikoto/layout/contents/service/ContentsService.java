package com.misakamikoto.layout.contents.service;

import com.misakamikoto.layout.contents.mapper.ContentsMapper;
import com.misakamikoto.layout.contents.model.ContentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Misaka on 2016-03-16.
 */
@Service
public class ContentsService {
    /**
     * The Contents mapper.
     */
    @Autowired
    private ContentsMapper contentsMapper;

    /**
     * Gets newest content.
     *
     * @return the newest content
     */
    public ContentsVO getNewestContent() {
        return this.contentsMapper.getNewestContent();
    }

    public List<ContentsVO> getContentsList(int codeId) throws IOException {
        return this.contentsMapper.getContentsList(codeId);
    }
}
