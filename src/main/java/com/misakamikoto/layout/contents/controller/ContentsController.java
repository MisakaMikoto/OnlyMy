package com.misakamikoto.layout.contents.controller;

import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.layout.contents.service.ContentsService;
import com.misakamikoto.layout.contents.service.GoogleDriveService;
import com.misakamikoto.layout.contents.service.GoogleYoutubeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The type Contents controller.
 */
@RestController
@RequestMapping("/contents")
public class ContentsController {
    private static Logger logger = LoggerFactory.getLogger(ContentsController.class);

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private GoogleYoutubeService googleYoutubeService;

    @Autowired
    private GoogleDriveService googleDriveService;

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

        return this.googleYoutubeService.upload(file, title, description, tags);
    }

    /**
     * Upload picture.
     *
     * @param files       the files
     * @param codeId      the code id
     * @param subject     the subject
     * @param description the description
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/drive/upload/insert", method = RequestMethod.POST)
    public void uploadPicture(@RequestParam("file") MultipartFile[] files,
                              @RequestParam("codeId") int codeId,
                              @RequestParam("uploadTitle") String subject,
                              @RequestParam("uploadDescription") String description) throws IOException {

        this.googleDriveService.upload(files, codeId, subject, description);
    }

    /**
     * List youtube list.
     *
     * @return the list
     */
    @RequestMapping(value = "/youtube/upload/list", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> listYoutube() {
        return this.googleYoutubeService.getUploadList();
    }

    /**
     * List picture list.
     *
     * @param codeId the code id
     * @return the list
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/upload/list/{codeId}", method = RequestMethod.GET)
    public @ResponseBody List<ContentsVO> listPicture(@PathVariable("codeId") int codeId) throws IOException {
        return this.contentsService.getContentsList(codeId);
    }
}
