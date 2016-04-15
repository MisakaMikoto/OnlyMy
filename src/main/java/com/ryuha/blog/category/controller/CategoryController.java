package com.ryuha.blog.category.controller;

import com.ryuha.blog.category.model.CategoryVO;
import com.ryuha.blog.category.service.CategoryService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Misaka on 2016-03-08.
 */

@Controller(value = "categoryController")
@RequestMapping("/category")
public class CategoryController {
    @Resource(name = "categoryService")
    public CategoryService categoryService;

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String loadList() {
        JSONArray jsonArray = new JSONArray();
        for(CategoryVO categoryVO : this.categoryService.getCategoryList()) {
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put(categoryVO.getName(), this.categoryService.getCount(categoryVO.getId()));
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }
}
