package com.misakamikoto.layout.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misakamikoto.layout.category.mapper.CategoryMapper;
import com.misakamikoto.layout.category.model.CategoryVO;

/**
 * Created by Misaka on 2016-03-08.
 */
@Service
public class CategoryService {
    /**
     * The Category mapper.
     */
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * Gets category list.
     *
     * @return the category list
     */
    public List<CategoryVO> getCategoryList() {
        return this.categoryMapper.getCategoryList();
    }
}
