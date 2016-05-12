package com.misakamikoto.apigateway.test.controller;

import com.misakamikoto.apigateway.test.service.TestService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Misaka on 2016-05-02.
 */
@Controller(value = "apiGatewayController")
@RequestMapping("/apiGateway")
public class TestController {
    @Resource(name = "testService")
    TestService testService;


    /**
     * Load string.
     *
     * @return the string
     */
    @RequestMapping(method = RequestMethod.GET)
    public String load() {
        return "apigateway/test/index";
    }

    /**
     * Call get string.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/call/get", method = RequestMethod.GET)
    public @ResponseBody String callGet(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String uri = testService.parseUri(queryString);
        String parameter = testService.parseParameter(queryString);

        uri = uri + "?" + parameter;
        JSONObject response = testService.call("GET", uri, null);

        return response.toString();
    }

    /**
     * Call post string.
     *
     * @param queryString the query string
     * @return the string
     */
    @RequestMapping(value = "/call/post", method = RequestMethod.POST)
    public @ResponseBody String callPost(@RequestBody String queryString) {
        String uri = null;
        try {
            uri = testService.parseUri(URLDecoder.decode(queryString, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String parameter = testService.parseParameter(queryString);
        JSONObject response = testService.call("POST", uri, parameter);

        return response.toString();
    }
}
