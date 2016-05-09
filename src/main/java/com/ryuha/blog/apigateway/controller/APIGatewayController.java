package com.ryuha.blog.apigateway.controller;

import com.ryuha.blog.apigateway.service.APIGatewayService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Misaka on 2016-05-02.
 */
@Controller(value = "apiGatewayController")
@RequestMapping("/apiGateway")
public class APIGatewayController {
    @Resource(name = "apiGatewayService")
    APIGatewayService apiGatewayService;

    @RequestMapping(method = RequestMethod.GET)
    public String load() {
        return "apigateway/index";
    }

    @RequestMapping(value = "/call/get", method = RequestMethod.GET)
    public @ResponseBody String callGet(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String uri = apiGatewayService.parseUri(queryString);
        String parameter = apiGatewayService.parseParameter(queryString);

        uri = uri + "?" + parameter;
        JSONObject response = apiGatewayService.call("GET", uri, null);

        return response.toString();
    }

    @RequestMapping(value = "/call/post", method = RequestMethod.POST)
    public @ResponseBody String callPost(@RequestBody String queryString) {
        String uri = null;
        try {
            uri = apiGatewayService.parseUri(URLDecoder.decode(queryString, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String parameter = apiGatewayService.parseParameter(queryString);
        JSONObject response = apiGatewayService.call("POST", uri, parameter);

        return response.toString();
    }
}
