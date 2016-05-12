package com.ryuha.blog.apigateway.test.test.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Misaka on 2016-05-02.
 */
@Service(value = "apiGatewayService")
public class TestService {
    public static final String GET = "GET";
    public static final String POST = "POST";

    /**
     * Call json object.
     *
     * @param type      the type
     * @param uri       the uri
     * @param parameter the parameter
     * @return the json object
     */
    public JSONObject call(String type, String uri, String parameter) {
        String urlParameters;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(uri);

            if(POST.equals(type.toUpperCase()) && parameter != null) {
                urlParameters = parameter;
                jsonObject = this.httpURLConnectionHandler(POST, url, urlParameters);

            } else {
                jsonObject = this.httpURLConnectionHandler(GET, url, null);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Http url connection handler json object.
     *
     * @param method        the method
     * @param url           the url
     * @param urlParameters the url parameters
     * @return the json object
     */
    public JSONObject httpURLConnectionHandler(String method, URL url, String urlParameters) {
        StringBuffer responseText = null;
        BufferedReader in = null;
        HttpURLConnection connection = null;

        try {
            if(POST.equals(method) && urlParameters != null) {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

            // GET
            } else {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
            }

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("parameters : " + urlParameters);
            System.out.println("Response Code : " + connection.getResponseCode());

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            responseText = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseText.append(inputLine);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject();
                jsonObject.put("responseCode", connection.getResponseCode());
                jsonObject.put("responseHeaders", connection.getHeaderFields().toString());
                jsonObject.put("responseText", (responseText != null) ? responseText.toString() : "");

                if(in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            connection.disconnect();

            return jsonObject;
        }
    }

    /**
     * Parse uri string.
     *
     * @param queryString the query string
     * @return the string
     */
    public String parseUri(String queryString) {
        String uri = null;
        if(queryString.indexOf("&") > -1) {
            for(int i = 0; i < queryString.split("&").length; i++) {
                if("uri".equals(queryString.split("&")[i].split("=")[0])) {
                    uri = queryString.split("&")[i].split("=")[1];
                }
            }

        } else {
            uri = queryString.split("=")[1];
        }
        return uri;
    }

    /**
     * Parse parameter string.
     *
     * @param queryString the query string
     * @return the string
     */
    public String parseParameter(String queryString) {
        StringBuffer parameter = null;
        if(queryString.indexOf("&") > -1) {
            for (int i = 0; i < queryString.split("&").length; i++) {
                if (!("uri".equals(queryString.split("&")[i].split("=")[0]))) {
                    parameter.append(queryString.split("&")[i]);
                }
            }
        }
        return (parameter != null) ? parameter.toString() : "";
    }
}
