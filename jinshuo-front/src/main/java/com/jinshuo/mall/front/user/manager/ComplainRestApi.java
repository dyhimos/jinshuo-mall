package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.ComplainCmd;
import com.jinshuo.mall.service.user.application.qry.ComplainQry;
import com.jinshuo.mall.service.user.service.command.ComplainCmdService;
import com.jinshuo.mall.service.user.service.query.ComplainQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/v1/manager/complain")
@Api(description = " app版本管理")
public class ComplainRestApi{

	 @Autowired
    private ComplainCmdService complainCmdService;
    
     @Autowired
    private ComplainQueryService complainQueryService;

    /**
     * 查询投诉列表信息
     * @return
     */
    @PostMapping("/queryComplainList")
    @ApiOperation("查询投诉列表信息")
    public WrapperResponse queryList(ComplainQry query){
        return WrapperResponse.success(complainQueryService.queryList(query));
    }


    /**
     * 查询投诉详情
     * @return
     */
    @GetMapping("/queryComplainDetail")
    @ApiOperation("查询投诉详情")
    public WrapperResponse queryComplainDetail(@RequestParam("id") Long id){
        return WrapperResponse.success(complainQueryService.queryComplainDetail(id));
    }


    /**
     * 删除投诉信息
     * @param complainCmd
     * @return
     */
    @PostMapping("/deleteComplain")
    @ApiOperation("删除投诉信息")
    public WrapperResponse deleteRole(@RequestBody ComplainCmd complainCmd){
        complainCmdService.delete(complainCmd);
        return WrapperResponse.success();
    }
}

	
