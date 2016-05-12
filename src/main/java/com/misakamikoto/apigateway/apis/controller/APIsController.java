package com.misakamikoto.apigateway.apis.controller;

import com.misakamikoto.apigateway.apis.service.APIsService;
import com.misakamikoto.apigateway.apis.model.APIsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Misaka on 2016-05-12.
 */

@Controller(value = "apisController")
@RequestMapping("/apis")
public class APIsController {
    @Resource(name = "apisService")
    APIsService apisService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<APIsVO> loadList() {
        return this.apisService.getList();
    }
}
