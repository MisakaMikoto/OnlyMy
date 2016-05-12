package com.misakamikoto.layout.category.service;

import com.misakamikoto.layout.category.mapper.CategoryMapper;
import com.misakamikoto.layout.category.model.CategoryVO;
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
}
