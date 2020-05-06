package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.qry.AppVersionQry;
import com.jinshuo.mall.service.user.service.query.AppVersionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/v1/wx/appVersion")
@Api(description = " 微信app版本管理")
public class WxAppVersionRestApi {

     @Autowired
    private AppVersionQueryService appVersionQueryService;

    /**
     * 查询app版本管理列表
     * @return
     */
    @PostMapping("/queryAppVersionList")
    @ApiOperation("查询app版本管理信息")
    public WrapperResponse queryList(@RequestBody AppVersionQry query){
        return WrapperResponse.success(appVersionQueryService.queryList(query));
    }


    /**
     * 下载最新app
     * @return
     */
    @GetMapping("/downLatestAppVersion")
    @ApiOperation("检查app是否要更新")
    public WrapperResponse downLatestAppVersion(){
        return WrapperResponse.success(appVersionQueryService.downLatestAppVersion());
    }

    /**
     * 检查app是否要更新
     * @return
     */
    @GetMapping("/queryLatestAppVersion")
    @ApiOperation("检查app是否要更新")
    public WrapperResponse queryLatestAppVersion(@RequestParam("version") Integer version){
        return WrapperResponse.success(appVersionQueryService.queryLatestAppVersion(version));
    }
}

	
