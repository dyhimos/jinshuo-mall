package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.user.model.role.Role;
import com.jinshuo.mall.service.user.application.cmd.RoleCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.service.command.RoleCmdService;
import com.jinshuo.mall.service.user.service.query.RoleQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色api
* @Description:
* @Author:         mgh
* @CreateDate:     2019/8/29 11:59
* @UpdateUser:     mgh
* @UpdateDate:     2019/8/29 11:59
* @UpdateRemark:
* @Version:        1.0
*/
@RestController
@Validated
@RequestMapping("/v1/manager/role")
@Api(description = "角色后台操作")
public class RoleRestApi {

    @Autowired
    private RoleCmdService roleCmdService;

    @Autowired
    private RoleQueryService roleQueryService;

    /**
     * 保存角色信息
     * @param roleCmd
     * @return
     */
    @PostMapping("/saveRole")
    @ApiOperation("保存角色信息")
    public WrapperResponse save(@RequestBody RoleCmd roleCmd){
        roleCmdService.save(roleCmd);
        return WrapperResponse.success();
    }

    /**
     * 查询角色对应的权限信息（一维数组）
     * @param roleCmd
     * @return
     */
    @PostMapping("/queryMenusByRoleId")
    @ApiOperation("查询角色对应的权限信息（一维数组）")
    public WrapperResponse queryMenusByRoleId(@RequestBody RoleCmd roleCmd){
        return WrapperResponse.success(roleQueryService.queryMenusByRoleId(roleCmd));
    }


    /**
     * 查询角色信息（包括权限）
     * @param roleCmd
     * @return
     */
    @PostMapping("/queryByRoleId")
    @ApiOperation("查询角色信息（包括权限）")
    public WrapperResponse queryByRoleId(@RequestBody RoleCmd roleCmd){
        return WrapperResponse.success(roleQueryService.queryByRoleId(roleCmd));
    }

    /**
     * 删除角色信息
     * @param roleCmd
     * @return
     */
    @PostMapping("/deleteRole")
    @ApiOperation("删除角色信息")
    public WrapperResponse deleteRole(@RequestBody RoleCmd roleCmd){
        roleCmdService.delete(roleCmd);
        return WrapperResponse.success();
    }

    /**
     * 查询店铺角色列表
     * @param shopIdCmd
     * @return
     */
    @PostMapping("/queryRoleList")
    @ApiOperation("查询店铺角色列表")
    public WrapperResponse queryRoleList(@RequestBody ShopIdCmd shopIdCmd){
        List<Role> roleList= roleQueryService.queryRoleList(shopIdCmd);
        return WrapperResponse.success(roleList);
    }
}
