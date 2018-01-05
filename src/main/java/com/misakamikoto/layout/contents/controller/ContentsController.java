package com.misakamikoto.layout.contents.controller;

import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.layout.contents.service.ContentsService;
import com.misakamikoto.layout.contents.service.YoutubeUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Misaka on 2016-03-08.
 */
@RestController
@RequestMapping("/contents")
public class ContentsController {
    private static Logger logger = LoggerFactory.getLogger(ContentsController.class);

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
     * @param codeId the category code
     * @return the contents list
     */
    @RequestMapping(value = "/list/{codeId}", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> getContentsList(@PathVariable("codeId") int codeId) {
        return this.contentsService.getContentsList(codeId);
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
    @RequestMapping(value = "/youtube/upload/insert", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") final MultipartFile file,
                       @RequestParam("uploadTitle") String title,
                       @RequestParam("uploadDescription") String description,
                       @RequestParam("uploadTags") String tags) {

        return this.youtubeUploadService.upload(file, title, description, tags);
    }

    /**
     * Upload string.
     *
     * @return the string
     */
    @RequestMapping(value = "/youtube/upload/list", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> uploadList() {
        return this.youtubeUploadService.myUploads();
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
