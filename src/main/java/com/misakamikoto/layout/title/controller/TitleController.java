package com.misakamikoto.layout.title.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.misakamikoto.layout.title.model.TitleVO;
import com.misakamikoto.layout.title.service.TitleService;

/**
 * Created by Misaka on 2016-03-16.
 */

@RestController(value = "titleController")
@RequestMapping("/title")
public class TitleController {
    @Resource(name = "titleService")
    public TitleService titleService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody TitleVO getName() {
        return this.titleService.getName();
    }
}
