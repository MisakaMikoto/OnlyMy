package com.ryuha.blog.category.service;

import com.ryuha.blog.category.mapper.CategoryMapper;
import com.ryuha.blog.category.model.CategoryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Misaka on 2016-03-08.
 */

@Service(value = "categoryService")
public class CategoryService {
    @Resource(name = "categoryMapper")
    private CategoryMapper categoryMapper;

    public List<CategoryVO> getCategoryList() {
        return this.categoryMapper.getCategoryList();
    }
    public int getCount(String categoryId) {
        return this.categoryMapper.getCount(categoryId);
    }
}
