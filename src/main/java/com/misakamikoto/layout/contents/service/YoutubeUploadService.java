package com.misakamikoto.layout.contents.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import com.misakamikoto.layout.contents.model.ContentsVO;
import com.misakamikoto.websocket.ClientWebSocket;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Misaka on 2016-06-01.
 */
@Service
public class YoutubeUploadService {
    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    /** Global instance of Youtube object to make all API requests. */
    private static YouTube youtube;

    /* Global instance of the format used for the video being uploaded (MIME type). */
    private static String VIDEO_FILE_FORMAT = "video/*";


    /**
     * Authorizes the installed application to access user's protected data.
     *
     * @param scopes list of scopes needed to run youtube upload.
     */
    private static Credential authorize(List<String> scopes) throws Exception {
        // Load client secrets.
        Reader clientSecretReader = new InputStreamReader(YoutubeUploadService.class.getResourceAsStream("/youtube/client_secrets.json"));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

        // Checks that the defaults have been replaced (Default = "Enter X here").
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from https://code.google.com/apis/console/?api=youtube"
                            + "into youtube-cmdline-uploadvideo-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // Set up file credential store.
        FileCredentialStore credentialStore = new FileCredentialStore(
                new File(System.getProperty("user.home"), ".credentials/youtube-api-uploadvideo.json"),
                JSON_FACTORY);

        // Set up authorization code flow.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialStore(credentialStore)
                .build();

        // Build the local server and bind it to port 9000
        LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(9000).build();

        // Authorize.
        return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
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
    public String upload(MultipartFile file, String title, String description, String tags) {
        Video returnedVideo = null;

        // Scope required to upload to YouTube.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

        try {
            // Authorize the request.
            Credential credential = authorize(scopes);

            // YouTube object used to make all API requests.
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-uploadvideo-sample").build();

            // We get the user selected local video file to upload.
            File videoFile = new File(file.getName());
            file.transferTo(videoFile);
            System.out.println("You chose " + videoFile + " to upload.");

            // Add extra information to the video before uploading.
            Video videoObjectDefiningMetadata = new Video();

      /*
       * Set the video to public, so it is available to everyone (what most people want). This is
       * actually the default, but I wanted you to see what it looked like in case you need to set
       * it to "unlisted" or "private" via API.
       */
            VideoStatus status = new VideoStatus();
            status.setPrivacyStatus("public");
            videoObjectDefiningMetadata.setStatus(status);

            // We set a majority of the metadata with the VideoSnippet object.
            VideoSnippet snippet = new VideoSnippet();

      /*
       * The Calendar instance is used to create a unique name and description for test purposes, so
       * you can see multiple files being uploaded. You will want to remove this from your project
       * and use your own standard names.
       */
            Calendar cal = Calendar.getInstance();
            snippet.setTitle(title);
            snippet.setDescription(description + "on " + cal.getTime());

            // Set your keywords.
            List<String> tagList = new ArrayList<String>();
            String[] parseTags = tags.split(",");
            for(String tag : parseTags) {
                tagList.add(tag);
            }
            snippet.setTags(tagList);

            // Set completed snippet to the video object.
            videoObjectDefiningMetadata.setSnippet(snippet);

            InputStreamContent mediaContent = new InputStreamContent(
                    VIDEO_FILE_FORMAT, new BufferedInputStream(new FileInputStream(videoFile)));
            mediaContent.setLength(videoFile.length());

      /*
       * The upload command includes: 1. Information we want returned after file is successfully
       * uploaded. 2. Metadata we want associated with the uploaded video. 3. Video file itself.
       */
            YouTube.Videos.Insert videoInsert = youtube.videos()
                    .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

            // Set the upload type and add event listener.
            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

      /*
       * Sets whether direct media upload is enabled or disabled. True = whole media content is
       * uploaded in a single request. False (default) = resumable media upload protocol to upload
       * in data chunks.
       */
            uploader.setDirectUploadEnabled(false);
            final ClientWebSocket clientWebSocket = new ClientWebSocket("ws://127.0.0.1:8080/serverWebsocket");

            MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
                public void progressChanged(MediaHttpUploader uploader) throws IOException {
                    switch (uploader.getUploadState()) {
                        case INITIATION_STARTED:
                            System.out.println("Initiation Started");
                            break;

                        case INITIATION_COMPLETE:
                            System.out.println("Initiation Completed");
                            break;

                        case MEDIA_IN_PROGRESS:
                            double substringDouble = Double.parseDouble(String.valueOf(uploader.getProgress()).substring(0, 6));
                            double percentage = Double.parseDouble(String.format("%.3f", substringDouble * 100));
                            clientWebSocket.sendMessage(String.valueOf(percentage));
                            System.out.println("Upload in progress");
                            System.out.println("Upload percentage: " + percentage);
                            break;

                        case MEDIA_COMPLETE:
                            clientWebSocket.sendMessage("100");
                            System.out.println("Upload Completed!");
                            break;

                        case NOT_STARTED:
                            System.out.println("Upload Not Started!");
                            break;
                    }
                }
            };
            uploader.setProgressListener(progressListener);

            // Execute upload.
            returnedVideo = videoInsert.execute();

            // Print out returned results.
            System.out.println("\n================== Returned Video ==================\n");
            System.out.println("  - Id: " + returnedVideo.getId());
            System.out.println("  - Title: " + returnedVideo.getSnippet().getTitle());
            System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
            System.out.println("  - Privacy Status: " + returnedVideo.getStatus().getPrivacyStatus());
            System.out.println("  - Video Count: " + returnedVideo.getStatistics().getViewCount());

        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
        return returnedVideo.getId();
    }

    public List<ContentsVO> getUploadList() {
        List<ContentsVO> contentsVOList = null;

        // Scope required to upload to YouTube.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorization.
            Credential credential = authorize(scopes);

            // YouTube object used to make all API requests.
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-myuploads-sample").build();

      /*
       * Now that the user is authenticated, the app makes a channel list request to get the
       * authenticated user's channel. Returned with that data is the playlist id for the uploaded
       * videos. https://developers.google.com/youtube/v3/docs/channels/list
       */
            YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
            channelRequest.setMine(true);
      /*
       * Limits the results to only the data we needo which makes things more efficient.
       */
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelResult = channelRequest.execute();

      /*
       * Gets the list of channels associated with the user. This sample only pulls the uploaded
       * videos for the first channel (default channel for user).
       */
            List<Channel> channelsList = channelResult.getItems();

            if (channelsList != null) {
                // Gets user's default channel id (first channel in list).
                String uploadPlaylistId =
                        channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();

                // List to store all PlaylistItem items associated with the uploadPlaylistId.
                List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

        /*
         * Now that we have the playlist id for your uploads, we will request the playlistItems
         * associated with that playlist id, so we can get information on each video uploaded. This
         * is the template for the list call. We call it multiple times in the do while loop below
         * (only changing the nextToken to get all the videos).
         * https://developers.google.com/youtube/v3/docs/playlistitems/list
         */
                YouTube.PlaylistItems.List playlistItemRequest =
                        youtube.playlistItems().list("id,contentDetails,snippet");
                playlistItemRequest.setPlaylistId(uploadPlaylistId);

                // This limits the results to only the data we need and makes things more efficient.
                playlistItemRequest.setFields(
                        "items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo");

                String nextToken = "";

                // Loops over all search page results returned for the uploadPlaylistId.
                do {
                    playlistItemRequest.setPageToken(nextToken);
                    PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                    playlistItemList.addAll(playlistItemResult.getItems());

                    nextToken = playlistItemResult.getNextPageToken();

                } while (nextToken != null);

                // Create uploadListJSON and Prints results.
                contentsVOList = createUploadListJSON(playlistItemList.size(), playlistItemList.iterator());
            }

        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return contentsVOList;
    }

    /*
   * Method that prints all the PlaylistItems in an Iterator.
   *
   * @param size size of list
   *
   * @param iterator of Playlist Items from uploaded Playlist
   */
    private List<ContentsVO> createUploadListJSON (int size, Iterator<PlaylistItem> playlistEntries) {
        List<ContentsVO> contentsVOList = new ArrayList<ContentsVO>();

        System.out.println("=============================================================");
        System.out.println("\t\tTotal Videos Uploaded: " + size);
        System.out.println("=============================================================\n");

        while (playlistEntries.hasNext()) {
            PlaylistItem playlistItem = playlistEntries.next();
            System.out.println(" video name  = " + playlistItem.getSnippet().getTitle());
            System.out.println(" video id    = " + playlistItem.getContentDetails().getVideoId());
            System.out.println(" upload date = " + playlistItem.getSnippet().getPublishedAt());
            System.out.println("\n-------------------------------------------------------------\n");

            ContentsVO contentsVO = new ContentsVO();
            contentsVO.setSubject(playlistItem.getSnippet().getTitle());
            contentsVO.setVideoId(playlistItem.getContentDetails().getVideoId());
            contentsVOList.add(contentsVO);
        }
        return contentsVOList;
    }
}
