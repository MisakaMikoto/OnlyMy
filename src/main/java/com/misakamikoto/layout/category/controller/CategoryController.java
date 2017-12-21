package com.misakamikoto.layout.category.controller;

import com.misakamikoto.layout.category.model.CategoryVO;
import com.misakamikoto.layout.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Misaka on 2016-03-08.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * The Category service.
     */
    @Autowired
    public CategoryService categoryService;

    /**
     * Load list list.
     *
     * @return the list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<CategoryVO> loadList() {
        return this.categoryService.getCategoryList();
    }
}
