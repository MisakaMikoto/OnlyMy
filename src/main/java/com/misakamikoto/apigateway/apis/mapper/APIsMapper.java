package com.misakamikoto.apigateway.apis.mapper;

import com.misakamikoto.apigateway.apis.model.APIsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Misaka on 2016-05-12.
 */

@Repository(value = "apisMapper")
public interface APIsMapper {
    List<APIsVO> getList();
}
