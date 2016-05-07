package com.ryuha.blog.apigateway.controller;

import com.ryuha.blog.apigateway.service.TYKService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Misaka on 2016-05-02.
 */
@Controller(value = "apiGatewayController")
@RequestMapping("/apiGateway")
public class APIGatewayController {
    @Resource(name = "tykService")
    public TYKService tykService;

    @RequestMapping(method = RequestMethod.GET)
    public String load() {
        return "apigateway/tyk/index";
    }
}
