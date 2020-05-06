package com.jinshuo.mall.front.user.manager;

import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.service.user.application.cmd.MenuCmd;
import com.jinshuo.mall.service.user.application.cmd.RoleIdMenuListCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.service.command.MeunCmdService;
import com.jinshuo.mall.service.user.service.query.MenuQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单
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
@RequestMapping("/v1/manager/menu")
@Api(description = "菜单后台操作")
public class MenuRestApi {

    @Autowired
    private MeunCmdService meunCmdService;

    @Autowired
    private MenuQueryService menuQueryService;


    /**
     * 新增菜单信息
     * @param menuCmd
     * @return
     */
    @PostMapping("/addMenu")
    @ApiOperation("新增菜单信息")
    public WrapperResponse save(@RequestBody MenuCmd menuCmd){
        meunCmdService.save(menuCmd);
        return WrapperResponse.success();
    }

    /**
     * 更新菜单信息
     * @param menuCmd
     * @return
     */
    @PostMapping("/updateMenu")
    @ApiOperation("更新菜单信息")
    public WrapperResponse updateMenu(@RequestBody MenuCmd menuCmd){
        meunCmdService.update(menuCmd);
        return WrapperResponse.success();
    }

    /**
     * 删除菜单信息
     * @param menuCmd
     * @return
     */
    @PostMapping("/deleteRole")
    @ApiOperation("删除菜单信息")
    public WrapperResponse deleteRole(@RequestBody MenuCmd menuCmd){
        meunCmdService.delete(menuCmd);
        return WrapperResponse.success();
    }


    /**
     * 查询所有菜单(树状)
     * @param
     */
    @PostMapping("/queryAllMenu")
    @ApiOperation("查询所有菜单(树状)")
    public WrapperResponse getAllMenuTree(){
        return WrapperResponse.success(menuQueryService.getAllMenuTree());
    }

    /**
     * 获取角色对应的菜单
     * @param roleIdMenuListCmd 角色列表list
     * @return
     */
    @PostMapping("/queryMenuByRoleId")
    @ApiOperation("获取角色对应的菜单")
    public WrapperResponse queryByRoleId(@RequestBody RoleIdMenuListCmd roleIdMenuListCmd){
        return WrapperResponse.success(meunCmdService.queryByRoleId(roleIdMenuListCmd));
    }


   /* *//**
     * 根据角色ID查询对应的菜单
     * @param roleIdMenuListCmd 角色列表list
     * @return
     *//*
    @PostMapping("/queryMenuByRoleId")
    @ApiOperation("根据角色ID查询对应的菜单")
    public WrapperResponse queryMenuByRoleId(@RequestBody RoleIdMenuListCmd roleIdMenuListCmd){
        return WrapperResponse.success(meunCmdService.queryMenuByRoleId(roleIdMenuListCmd));
    }*/


    /**
     * 查询店铺的菜单列表
     * @param shopIdCmd
     * @return
     */
    @PostMapping("/queryMenuByShop")
    @ApiOperation("查询店铺的菜单列表")
    public WrapperResponse queryMenuByShop(@RequestBody ShopIdCmd shopIdCmd){
        return WrapperResponse.success(meunCmdService.queryMenuByShop(shopIdCmd));
    }
}
