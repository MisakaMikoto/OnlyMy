package com.ryuha.blog.contents.controller;

import com.ryuha.blog.category.service.CategoryService;
import com.ryuha.blog.contents.model.ContentsVO;
import com.ryuha.blog.contents.service.ContentsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Misaka on 2016-03-16.
 */
@Controller(value = "contentsController")
@RequestMapping("/contents")
public class ContentsController {
    @Resource(name = "contentsService")
    public ContentsService contentsService;

    @RequestMapping(value = "/newest",  produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String getNewsetContents() {
        JSONObject jsonObject = new JSONObject();
        ContentsVO contentsVO = this.contentsService.getNewestContents();
        try {
            jsonObject.put(contentsVO.getSubject(), contentsVO.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/list",  produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String getContentsList(@RequestParam String categoryCode) {
        JSONArray jsonArray = new JSONArray();
        for(ContentsVO contentsVO : this.contentsService.getContentsList(categoryCode)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(String.valueOf(contentsVO.getId()), contentsVO.getSubject());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @RequestMapping(value = "/content",  produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String getContent(@RequestParam int contentId) {
        JSONObject jsonObject = new JSONObject();
        ContentsVO contentsVO = this.contentsService.getContent(contentId);
        try {
            jsonObject.put(contentsVO.getSubject(), contentsVO.getContent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
