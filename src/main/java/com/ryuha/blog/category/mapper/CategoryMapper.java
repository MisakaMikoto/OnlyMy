package com.ryuha.blog.category.mapper;

import com.ryuha.blog.category.model.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Misaka on 2016-03-14.
 */
@Repository(value = "categoryMapper")
public interface CategoryMapper {
    List<CategoryVO> getCategoryList();
    int getCount(String categoryCode);

}
