package com.misakamikoto.layout.contents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.layout.contents.service.ContentsService;
import com.misakamikoto.layout.contents.service.YoutubeUploadService;

/**
 * Created by Misaka on 2016-03-16.
 */
@RestController
@RequestMapping("/contents")
public class ContentsController {

    @Autowired
    public ContentsService contentsService;

    @Autowired
    public YoutubeUploadService youtubeUploadService;

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

    @Transactional
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") final MultipartFile file,
                       @RequestParam("uploadTitle") String title,
                       @RequestParam("uploadDescription") String description,
                       @RequestParam("uploadTags") String tags) {

        return youtubeUploadService.upload(file, title, description, tags);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("categoryCode") String categoryCode,
                      @RequestParam("title") String title,
                      @RequestParam("description") String description,
                      @RequestParam("videoId") String videoId) {

        contentsService.addContent(categoryCode, title, description, videoId);
    }
}
