package com.misakamikoto.layout.contents.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by MisakaMikoto on 2018. 1. 15..
 */
@Service
public class GoogleAuthorizeService {
    private static final String DRIVE = "DRIVE";

    private static final String YOUTUBE = "YOUTUBE";

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final Set<String> DRIVE_SCOPES = DriveScopes.all();


    private static final List<String> YOUTUBE_SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/youtube");

    private static final String SECRET = "/google/client_secrets.json";

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory YOUTUBE_DATA_STORE_FACTORY;

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DRIVE_DATA_STORE_FACTORY;

    /** Directory to store user credentials for this application. */
    private static final java.io.File YOUTUBE_DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/onlyMyYoutube");

    private static final java.io.File DRIVE_DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/onlyMyDrive");

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            YOUTUBE_DATA_STORE_FACTORY = new FileDataStoreFactory(YOUTUBE_DATA_STORE_DIR);
            DRIVE_DATA_STORE_FACTORY = new FileDataStoreFactory(DRIVE_DATA_STORE_DIR);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Authorizes the installed application to access user's protected data.
     *
     */
    public Credential authorizeYoutube() throws Exception {
        return this.authorize(YOUTUBE);
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public Credential authorizeDrive() throws IOException {
        return this.authorize(DRIVE);
    }

    private Credential authorize(String authorizeType) throws IOException {
        FileDataStoreFactory fileDataStoreFactory;
        GoogleAuthorizationCodeFlow flow = null;

        // Load client secrets.
        InputStream in =
                GoogleAuthorizeService.class.getResourceAsStream(SECRET);

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));


        if(YOUTUBE.equals(authorizeType)) {
            fileDataStoreFactory = YOUTUBE_DATA_STORE_FACTORY;

            // Build flow and trigger user authorization request.
            flow = new GoogleAuthorizationCodeFlow.Builder(
                            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, YOUTUBE_SCOPES)
                            .setDataStoreFactory(fileDataStoreFactory)
                            .build();

        } else if(DRIVE.equals(authorizeType)) {
            fileDataStoreFactory = DRIVE_DATA_STORE_FACTORY;

            // Build flow and trigger user authorization request.
            flow =  new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, DRIVE_SCOPES)
                    .setDataStoreFactory(fileDataStoreFactory)
                    .build();

        } else {
            // another conditions..
            ;
        }
        // Build the local server and bind it to port 9000
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(9000).build();

        // Authorize.
        return new AuthorizationCodeInstalledApp(
                flow, localReceiver).authorize("user");
    }
}
