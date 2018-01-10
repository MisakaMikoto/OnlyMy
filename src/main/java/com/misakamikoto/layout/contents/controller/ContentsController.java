package com.misakamikoto.layout.contents.controller;

import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.layout.contents.service.ContentsService;
import com.misakamikoto.layout.contents.service.PictureUploadService;
import com.misakamikoto.layout.contents.service.YoutubeUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The type Contents controller.
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

    @Autowired
    public PictureUploadService pictureUploadService;

    /**
     * Gets contents list.
     *
     * @param codeId the code id
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
     * Upload youtube string.
     *
     * @param file        the file
     * @param title       the title
     * @param description the description
     * @param tags        the tags
     * @return the string
     */
    @RequestMapping(value = "/youtube/upload/insert", method = RequestMethod.POST)
    public @ResponseBody String uploadYoutube(@RequestParam("file") MultipartFile file,
                                              @RequestParam("uploadTitle") String title,
                                              @RequestParam("uploadDescription") String description,
                                              @RequestParam("uploadTags") String tags) {

        return this.youtubeUploadService.upload(file, title, description, tags);
    }

    /**
     * Upload list list.
     *
     * @return the list
     */
    @RequestMapping(value = "/youtube/upload/list", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> listYoutube() {
        return this.youtubeUploadService.getUploadList();
    }

    @RequestMapping(value = "/picture/upload/insert", method = RequestMethod.POST)
    public void uploadPicture(@RequestParam("file") MultipartFile file,
                                              @RequestParam("uploadTitle") String title,
                                              @RequestParam("uploadDescription") String description) {

        this.pictureUploadService.upload(file, title, description);
    }

    @RequestMapping(value = "/picture/upload/list", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> listPicture() {
        return this.pictureUploadService.getUploadList();
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
