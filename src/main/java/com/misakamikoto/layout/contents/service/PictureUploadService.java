package com.misakamikoto.layout.contents.service;

import com.misakamikoto.layout.contents.model.ContentsVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MisakaMikoto on 2018. 1. 9..
 */
@Service
public class PictureUploadService {

    @Value("#{pictureConf['upload.path']}")
    private String uploadPath;

    public void upload(MultipartFile file, String title, String description) {
        System.out.println(title);
        System.out.println(description);
    }

    public List<ContentsVO> getUploadList() {
         return new ArrayList<ContentsVO>();
    }
}
