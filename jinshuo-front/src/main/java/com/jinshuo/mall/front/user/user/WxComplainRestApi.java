package com.jinshuo.mall.front.user.user;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.ComplainCmd;
import com.jinshuo.mall.service.user.application.qry.ComplainQry;
import com.jinshuo.mall.service.user.service.command.ComplainCmdService;
import com.jinshuo.mall.service.user.service.query.ComplainQueryService;
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
@RequestMapping("/v1/wx/complain")
@Api(description = " 投诉管理")
public class WxComplainRestApi {

	 @Autowired
    private ComplainCmdService complainCmdService;
    
     @Autowired
    private ComplainQueryService complainQueryService;


    /**
     * 查询投诉信息
     * @return
     */
    @PostMapping("/queryComplainList")
    @ApiOperation("查询投诉信息")
    public WrapperResponse queryList(ComplainQry query){
        return WrapperResponse.success(complainQueryService.queryList(query));
    }
    
    /**
     * 保存投诉信息
     * @param complainCmd
     * @return
     */
    @PostMapping("/saveComplain")
    @ApiOperation("保存投诉信息")
    public WrapperResponse save(@RequestBody ComplainCmd complainCmd){
        complainCmdService.save(complainCmd);
        return WrapperResponse.success();
    }


    /**
     * 删除app版本管理信息
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

	
