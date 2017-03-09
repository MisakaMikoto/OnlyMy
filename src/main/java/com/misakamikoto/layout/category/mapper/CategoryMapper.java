package com.misakamikoto.layout.category.mapper;

import java.util.List;
import com.misakamikoto.layout.category.model.CategoryVO;

/**
 * Created by Misaka on 2016-03-14.
 */
public interface CategoryMapper {

    /**
     * Gets category list.
     *
     * @return the category list
     */
    List<CategoryVO> getCategoryList();
}
