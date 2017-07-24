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

    /**
     * The Contents service.
     */
    @Autowired
    public ContentsService contentsService;

    /**
     * The Youtube upload service.
     */
    @Autowired
    public YoutubeUploadService youtubeUploadService;


    /**
     * Gets contents list.
     *
     * @param categoryCode the category code
     * @return the contents list
     */
    @RequestMapping(value = "/list/{categoryCode}", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> getContentsList(@PathVariable("categoryCode") String categoryCode) {
        return this.contentsService.getContentsList(categoryCode);
    }


    /**
     * Gets newest content.
     *
     * @return the newest content
     */
    @RequestMapping(value = "/newest", method = RequestMethod.GET)
    public @ResponseBody ContentsVO getNewestContent() {
        return this.contentsService.getNewestContent();
    }


    /**
     * Gets content.
     *
     * @param contentId the content id
     * @return the content
     */
    @RequestMapping(value = "/{contentId}", method = RequestMethod.GET)
    public @ResponseBody ContentsVO getContent(@PathVariable("contentId") int contentId) {
        return this.contentsService.getContent(contentId);
    }


    /**
     * Upload string.
     *
     * @param file        the file
     * @param title       the title
     * @param description the description
     * @param tags        the tags
     * @return the string
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") final MultipartFile file,
                       @RequestParam("uploadTitle") String title,
                       @RequestParam("uploadDescription") String description,
                       @RequestParam("uploadTags") String tags) {

        return this.youtubeUploadService.upload(file, title, description, tags);
    }


    /**
     * Add.
     *
     * @param categoryCode the category code
     * @param title        the title
     * @param description  the description
     * @param videoId      the video id
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestParam("categoryCode") String categoryCode,
                      @RequestParam("title") String title,
                      @RequestParam("description") String description,
                      @RequestParam("videoId") String videoId) {

        this.contentsService.addContent(categoryCode, title, description, videoId);
    }
}
