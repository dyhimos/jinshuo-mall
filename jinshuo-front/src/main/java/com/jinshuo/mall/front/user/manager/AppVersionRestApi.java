package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.AppVersionCmd;
import com.jinshuo.mall.service.user.application.qry.AppVersionQry;
import com.jinshuo.mall.service.user.service.command.AppVersionCmdService;
import com.jinshuo.mall.service.user.service.query.AppVersionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/v1/manager/appVersion")
@Api(description = " app版本管理")
public class AppVersionRestApi{

	 @Autowired
    private AppVersionCmdService appVersionCmdService;
    
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
     * 保存app版本管理信息
     * @param appVersionCmd
     * @return
     */
    @PostMapping("/saveAppVersion")
    @ApiOperation("保存app版本管理信息")
    public WrapperResponse save(@RequestBody AppVersionCmd appVersionCmd){
        appVersionCmdService.save(appVersionCmd);
        return WrapperResponse.success();
    }


    /**
     * 删除app版本管理信息
     * @param appVersionCmd
     * @return
     */
    @PostMapping("/deleteAppVersion")
    @ApiOperation("删除app版本管理信息")
    public WrapperResponse deleteRole(@RequestBody AppVersionCmd appVersionCmd){
        appVersionCmdService.delete(appVersionCmd);
        return WrapperResponse.success();
    }
}

	
