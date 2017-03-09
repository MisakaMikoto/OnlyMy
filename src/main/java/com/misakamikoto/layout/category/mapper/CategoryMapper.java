package com.misakamikoto.layout.category.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.misakamikoto.layout.category.model.CategoryVO;

/**
 * Created by Misaka on 2016-03-14.
 */
@Repository
public interface CategoryMapper {
    List<CategoryVO> getCategoryList();
}
