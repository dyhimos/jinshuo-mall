package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.qry.UserVisitorLogQry;
import com.jinshuo.mall.service.user.service.command.UserVisitorLogQueryService;
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
@RequestMapping("/v1/manager/userVisitorLog")
@Api(description = " ")
public class UserVisitorLogRestApi{


    @Autowired
    private UserVisitorLogQueryService userVisitorLogQueryService;

    /**
     * 查询列表
     * @return
     */
    @PostMapping("/queryUserVisitorLogList")
    @ApiOperation("查询信息")
    public WrapperResponse queryList(@RequestBody UserVisitorLogQry query){
        return WrapperResponse.success(userVisitorLogQueryService.queryList(query));
    }
}

	
