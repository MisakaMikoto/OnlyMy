package com.misakamikoto.layout.category.controller;

import com.misakamikoto.layout.category.model.CategoryVO;
import com.misakamikoto.layout.category.service.CategoryService;
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
