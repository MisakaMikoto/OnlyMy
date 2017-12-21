package com.misakamikoto.layout.title.controller;

import com.misakamikoto.layout.title.model.TitleVO;
import com.misakamikoto.layout.title.service.TitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Misaka on 2016-03-16.
 */
@RestController
@RequestMapping("/title")
public class TitleController {
    private static Logger logger = LoggerFactory.getLogger(TitleController.class);

    /**
     * The Title service.
     */
    @Autowired
    public TitleService titleService;

    /**
     * Gets name.
     *
     * @return the name
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody TitleVO getName() {
        TitleVO titleVo = null;

        try {
            titleVo = this.titleService.getName();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return titleVo;
    }


}
