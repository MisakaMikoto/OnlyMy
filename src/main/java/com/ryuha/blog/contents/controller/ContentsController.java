package com.ryuha.blog.contents.controller;

import com.ryuha.blog.contents.model.ContentsVO;
import com.ryuha.blog.contents.service.ContentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Misaka on 2016-03-16.
 */
@Controller(value = "contentsController")
@RequestMapping("/contents")
public class ContentsController {
    @Resource(name = "contentsService")
    public ContentsService contentsService;

    @RequestMapping(value = "/list/{categoryCode}", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> getContentsList(@PathVariable("categoryCode") String categoryCode) {
        return this.contentsService.getContentsList(categoryCode);
    }

    @RequestMapping(value = "/newest", method = RequestMethod.GET)
    public @ResponseBody ContentsVO getNewestContent() {
        return this.contentsService.getNewestContent();
    }

    @RequestMapping(value = "/{contentId}", method = RequestMethod.GET)
    public @ResponseBody ContentsVO getContent(@PathVariable("contentId") int contentId) {
        return this.contentsService.getContent(contentId);
    }
}
