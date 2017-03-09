package com.misakamikoto.oauth.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Misaka on 2016-04-18.
 */
@Service
public class FacebookOAuthService {
    private static FacebookOAuthService facebookOAuthService = null;

    public String getCode(String appId) {
        URL url;
        StringBuffer response = null;
        BufferedReader in;

        try {
            url = new URL("https://graph.facebook.com/oauth/authorize"
                    +"?client_id=" + appId
                    +"&redirect_uri=http://localhost:8080"
                    +"&scope=read_stream");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            //con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public JSONObject getAccessToken(String appId, String secret) {
        URL url;
        InputStream is = null;

        try {
            url = new URL("https://graph.facebook.com/oauth/access_token"
                    +"?client_id=" + appId
                    +"&client_secret=" + secret
                    +"&grant_type=client_credentials");

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            is = request.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer accessToken = new StringBuffer();
        Scanner scan = new Scanner(is, "UTF-8");

        while(scan.hasNextLine()) {
            accessToken.append(scan.nextLine());
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", accessToken.toString().substring(13));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static FacebookOAuthService getInstance() {
        if(facebookOAuthService == null)
        {
            facebookOAuthService = new FacebookOAuthService();
        }
        return facebookOAuthService;
    }
}