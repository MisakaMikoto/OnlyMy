package com.misakamikoto.apigateway.apis.service;

import com.misakamikoto.apigateway.apis.mapper.APIsMapper;
import com.misakamikoto.apigateway.apis.model.APIsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Misaka on 2016-05-12.
 */

@Service(value = "apisService")
public class APIsService {
    @Resource(name = "apisMapper")
    private APIsMapper apisMapper;

    public List<APIsVO> getList() {
        return this.apisMapper.getList();
    }
}
