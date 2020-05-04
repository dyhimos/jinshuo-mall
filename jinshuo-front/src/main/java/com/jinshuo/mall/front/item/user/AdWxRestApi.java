package com.jinshuo.mall.front.item.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.item.application.qry.AdQry;
import com.jinshuo.mall.service.item.service.query.AdPositionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 19458 on 2019/9/23.
 */
@RestController
@Api(description = "广告管理接口")
@RequestMapping("/v1/wx/ad")
public class AdWxRestApi {

    @Autowired
    private AdPositionQueryService adPositionQueryService;


    @PostMapping("/queryAdByCode")
    @ApiOperation(httpMethod = "POST", value = "根据广告位Code查询广告")
    public WrapperResponse queryAdByPositionId(@RequestBody AdQry qry) {
        return WrapperResponse.success(adPositionQueryService.queryAdByCode(qry));
    }

    @PostMapping("/queryPopAdByCode")
    @ApiOperation(httpMethod = "POST", value = "弹出广告查询")
    public WrapperResponse queryPopAdByCode(@RequestBody AdQry qry) {
        return WrapperResponse.success(adPositionQueryService.queryPopAdByCode(qry));
    }
}
