package com.jinshuo.mall.service.user.service.query;

import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.domain.user.model.role.Role;
import com.jinshuo.mall.domain.user.model.shop.ShopId;
import com.jinshuo.mall.service.user.application.cmd.RoleCmd;
import com.jinshuo.mall.service.user.application.cmd.RoleIdMenuListCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.application.dto.RoleDetailDto;
import com.jinshuo.mall.service.user.application.dto.RoleMenuTreeDto;
import com.jinshuo.mall.service.user.mybatis.MenuRepo;
import com.jinshuo.mall.service.user.mybatis.RoleRepo;
import com.jinshuo.mall.service.user.service.command.MeunCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色查询
 */
@Service
public class RoleQueryService {


    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private MeunCmdService meunCmdService;

    @Autowired
    private MenuRepo menuRepo;


    /**
     * 查询店铺角色列表
     *
     * @param
     * @return Address
     */
    public List<Role> queryRoleList(ShopIdCmd shopIdCmd) {
        Role role = Role.builder()
                .shopId(new ShopId(shopIdCmd.getShopId()))
                .build();
        return roleRepo.findAll(role);
    }

    /**
     * 查询角色信息以及角色对应的权限信息
     *
     * @param roleCmd
     * @return
     */
    public RoleDetailDto queryByRoleId(RoleCmd roleCmd) {
        if (roleCmd.getId() == null) {
            throw new UcBizException(UcReturnCode.UC200036.getMsg(), UcReturnCode.UC200036.getCode());
        }
        //获取角色信息
        Role role = roleRepo.findById(roleCmd.getId());

        if (role == null) {
            throw new UcBizException(UcReturnCode.UC200037.getMsg(), UcReturnCode.UC200037.getCode());
        }
        RoleIdMenuListCmd roleIdMenuListCmd = new RoleIdMenuListCmd();
        List<Long> ids = new ArrayList<>();
        ids.add(roleCmd.getId());
        roleIdMenuListCmd.setIds(ids);

        //获取角色菜单信息
        List<RoleMenuTreeDto> roleMenuTreeDtoList = meunCmdService.queryByRoleId(roleIdMenuListCmd);

        RoleDetailDto roleDetailDto = RoleDetailDto.builder()
                .id(role.getRoleId().getId())
                .code(role.getCode())
                .name(role.getName())
                .menus(roleMenuTreeDtoList)
                .build();
        return roleDetailDto;
    }


    public List<String> queryMenusByRoleId(RoleCmd roleCmd) {
        //获取角色信息
        Role role = roleRepo.findById(roleCmd.getId());

        if (role == null) {
            throw new UcBizException(UcReturnCode.UC200037.getMsg(), UcReturnCode.UC200037.getCode());
        }
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleCmd.getId());
        List<Menu> menuList = menuRepo.queryMenuByRoleId(roleIds);
        List<Long> menuLongIds = menuList.stream().map(t -> t.getMenuId().getId()).collect(Collectors.toList());
        List<String> menuIds = menuLongIds.stream().map(t -> String.valueOf(t)).collect(Collectors.toList());
        return menuIds;
    }
}
