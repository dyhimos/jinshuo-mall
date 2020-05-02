package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.cmd.FeatureCmd;
import com.jinshuo.mall.service.item.application.qry.LatticeQry;
import com.jinshuo.mall.service.item.service.command.FeatureComService;
import com.jinshuo.mall.service.item.service.query.FeatureQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by 19458 on 2019/9/25.
 */
@RestController
@Api(description = "产品属性管理接口")
@RequestMapping("/v1/manager/feature")
public class FeatureRestApi {

    @Autowired
    private FeatureQueryService featureQueryService;

    @Autowired
    private FeatureComService featureComService;


    @PostMapping("/create")
    @ApiOperation(value = "创建产品属性")
    public WrapperResponse create(@Valid @RequestBody FeatureCmd cmd) {
        return WrapperResponse.success(featureComService.save(cmd));
    }

    @PostMapping("/getFeature")
    @ApiOperation(httpMethod = "POST", value = "获取产品属性列表")
    public WrapperResponse create(@RequestBody LatticeQry qry ) {
        return WrapperResponse.success(featureQueryService.getAll(qry));
    }


}
