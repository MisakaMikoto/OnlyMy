package com.misakamikoto.layout.contents.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.misakamikoto.layout.contents.mapper.ContentsMapper;
import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.websocket.ClientWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by MisakaMikoto on 2018. 1. 9..
 */
@Service
@PropertySource({"classpath:google/drive_folder_id.properties",
                 "classpath:google/drive_rest_api.properties"})
public class GoogleDriveService {
    @Value("${picture.folder.id}")
    private String pictureFolderId;

    @Value("${rest.permissions.url}")
    private String permissionsUrl;

    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired
    private GoogleAuthorizeService googleAuthorizeService;

    @Autowired
    private ContentsMapper contentsMapper;

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    private Drive getDriveService(Credential credential) throws IOException {
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("OnlyMyDrive")
                .build();
    }

//    The supported conversions are available dynamically in the About resource's importFormats array and include:
//    From	To
//    Microsoft Word, OpenDocument Text, HTML, RTF, plain text	Google Docs
//    Microsoft Excel, OpenDocument Spreadsheet, CSV, TSV, plain text	Google Sheets
//    Microsoft Powerpoint, OpenDocument Presentation	Google Slides
//    JPEG, PNG, GIF, BMP, PDF	Google Docs (embeds the image in a Doc)
//    plain text (special MIME type), JSON	Google Apps Script

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=IOException.class)
    public void upload(MultipartFile[] files, int codeId, String subject, String description) throws IOException {
        ClientWebSocket clientWebSocket = new ClientWebSocket("ws://127.0.0.1:8080/serverWebSocket");

        // Authorize the request.
        Credential credential = googleAuthorizeService.authorizeDrive();
        Drive driveService = this.getDriveService(credential);

        for(int i = 0; i < files.length; i++) {
            File fileMetadata = new File();
            fileMetadata.setName(files[i].getOriginalFilename());
            fileMetadata.setParents(Collections.singletonList(pictureFolderId));

            java.io.File filePath = this.convertMultipartFileToFile(files[i]);
            FileContent mediaContent = new FileContent("image/jpeg", filePath);

            File file = driveService.files().create(fileMetadata, mediaContent)
                    .setFields("id, parents")
                    .execute();

            System.out.println("File ID: " + file.getId());

            ContentsVO contentsVO = new ContentsVO();
            contentsVO.setCodeId(codeId);
            contentsVO.setSubject(subject);
            contentsVO.setText(description);
            contentsVO.setViewUrl("https://drive.google.com/uc?id=" + file.getId());
            contentsVO.setUploadedId(file.getId());

            this.save(contentsVO);

            double progress = 100 * ((double)(i + 1) / files.length);
            System.out.println("**********************************" + progress + "% *************************");
            clientWebSocket.sendMessage(String.valueOf(progress));
        }
    }

    private java.io.File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        java.io.File convertFile = new java.io.File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(convertFile);

        return convertFile;
    }

    private void save(ContentsVO contentsVO) {
        this.contentsMapper.addPicture(contentsVO);
    }
}
