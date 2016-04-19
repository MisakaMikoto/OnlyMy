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
import java.util.List;

/**
 * Created by Misaka on 2016-03-08.
 */

@Controller(value = "categoryController")
@RequestMapping("/category")
public class CategoryController {
    @Resource(name = "categoryService")
    public CategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<CategoryVO> loadList() {
        return this.categoryService.getCategoryList();
    }
}
