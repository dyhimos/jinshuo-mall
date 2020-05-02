package com.jinshuo.mall.front.item.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.qry.PageQuery;
import com.jinshuo.mall.service.item.service.query.SpuQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 19458 on 2019/11/22.
 */
@RestController
@Api(description = "供应商后台管理接口")
@RequestMapping("/v1/manager/supplier")
public class WxSupplierRestApi {

    @Autowired
    private SpuQueryService spuQueryService;


    @PostMapping("/findBySupplierId")
    @ApiOperation(value = "获取供应商产品列表")
    public WrapperResponse findBySupplierId(PageQuery qry) {
        return WrapperResponse.success(spuQueryService.findBySupplierId(qry));
    }
}
