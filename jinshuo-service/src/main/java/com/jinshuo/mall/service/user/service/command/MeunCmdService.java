package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSON;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.domain.user.model.Menu.MenuId;
import com.jinshuo.mall.service.user.application.assermbler.MenuAssembler;
import com.jinshuo.mall.service.user.application.cmd.MenuCmd;
import com.jinshuo.mall.service.user.application.cmd.RoleIdMenuListCmd;
import com.jinshuo.mall.service.user.application.cmd.ShopIdCmd;
import com.jinshuo.mall.service.user.application.dto.*;
import com.jinshuo.mall.service.user.mybatis.MenuRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单service
 * @Author: dongyh
 * @CreateDate: 2019/11/4 10:28
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/11/4 10:28
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@Service
public class MeunCmdService {

    /**
     * 菜单repo
     */
    @Autowired
    private MenuRepo menuRepo;


    /**
     * 创建菜单
     *
     * @param menuCmd
     */
    public int save(MenuCmd menuCmd) {
        Menu menu = Menu.builder()
                .menuId(menuRepo.nextId())
                .sort(menuCmd.getSort())
                .name(menuCmd.getName())
                .code(menuCmd.getCode())
                .desc(menuCmd.getDesc())
                .pid(menuCmd.getPid())
                .type(menuCmd.getType())
                .menuType(menuCmd.getMenuType())
                .build();
        return menuRepo.save(menu);
    }


    /**
     * 更新菜单
     *
     * @param menuCmd
     */
    public int update(MenuCmd menuCmd) {
        Menu menu = Menu.builder()
                .menuId(new MenuId(menuCmd.getId()))
                .sort(menuCmd.getSort())
                .name(menuCmd.getName())
                .code(menuCmd.getCode())
                .desc(menuCmd.getDesc())
                .pid(menuCmd.getPid())
                .type(menuCmd.getType())
                .menuType(menuCmd.getMenuType())
                .build();
        return menuRepo.update(menu);
    }

    /**
     * 删除菜单
     *
     * @param menuCmd
     */
    public int delete(MenuCmd menuCmd) {
        return menuRepo.delete(menuCmd.getId());
    }

    /**
     * 查询角色对应的菜单列表
     *
     * @param roleIdMenuListCmd
     * @return
     */
    public List<RoleMenuTreeDto> queryByRoleId(RoleIdMenuListCmd roleIdMenuListCmd) {
        //查询所有菜单
        List<Menu> allMenu = menuRepo.findAll(null);

        //获取选中的菜单
        List<Menu> roleMenus = menuRepo.queryMenuByRoleId(roleIdMenuListCmd.getIds());
        //选中菜单的id集合
        List<Long> checkIds = roleMenus.stream().map(menu -> menu.getMenuId().getId()).collect(Collectors.toList());

        //查出一级菜单
        List<RoleMenuTreeDto> menuTreeList = allMenu.stream().filter(menu -> menu.getPid() == null || menu.getPid() == 0).map(menu ->
                MenuAssembler.assembleRoleMenuTreeDto(menu)).collect(Collectors.toList());
        getChildCheck(menuTreeList, allMenu, checkIds);
        return menuTreeList;
    }


    /**
     * 递归查询所有的子菜单
     *
     * @param topList          顶级菜单
     * @param distinctMenuList 所有菜单
     * @param checkIds         选中的菜单
     * @return
     */
    public void getChildCheck(List<RoleMenuTreeDto> topList, List<Menu> distinctMenuList, List<Long> checkIds) {
        topList.forEach(t -> {
                    //菜单列表
                    List<RoleMenuTreeDto> childList = distinctMenuList.stream().filter(
                            menu -> menu.getPid().equals(t.getId()) && menu.getMenuType() == 2)
                            .map(menu -> {
                                RoleMenuTreeDto roleMenuTreeDto = new RoleMenuTreeDto();
                                roleMenuTreeDto.setMenu(menu.getCode());
                                roleMenuTreeDto.setName(menu.getName());
                                roleMenuTreeDto.setId(menu.getMenuId().getId());
                                if (checkIds.contains(menu.getId())) {
                                    roleMenuTreeDto.setCheck(true);
                                } else {
                                    roleMenuTreeDto.setCheck(false);
                                }
                                return roleMenuTreeDto;
                            }).collect(Collectors.toList());

                    //按钮列表
                    List<ButtonDto> childBtnList = distinctMenuList.stream().filter(
                            menu -> menu.getPid().equals(t.getId()) && menu.getMenuType() == 3)
                            .map(menu -> {
                                ButtonDto buttonDto = new ButtonDto();
                                buttonDto.setBtn(menu.getCode());
                                buttonDto.setName(menu.getName());
                                if (checkIds.contains(menu.getId())) {
                                    buttonDto.setCheck(true);
                                } else {
                                    buttonDto.setCheck(false);
                                }
                                buttonDto.setId(menu.getId());
                                return buttonDto;
                            }).collect(Collectors.toList());
                    if (checkIds.contains(t.getId())) {
                        t.setCheck(true);
                    } else {
                        t.setCheck(false);
                    }
                    t.setSubMenu(childList);
                    t.setBtn(childBtnList);
                    if (childList.size() > 0) {
                        getChildCheck(childList, distinctMenuList, checkIds);
                    }
                }
        );
    }


    /**
     * 查询角色对应的菜单列表
     *
     * @param roleIdMenuListCmd
     * @return
     */
    public List<MenuTreeDto> queryMenuByRoleId(RoleIdMenuListCmd roleIdMenuListCmd) {
        List<Menu> roleMenus = menuRepo.queryMenuByRoleId(roleIdMenuListCmd.getIds());
        //查出一级菜单
        List<MenuTreeDto> menuTreeList = roleMenus.stream().filter(menu -> menu.getPid() == 0).map(menu ->
                MenuAssembler.assembleTreeDto(menu)).collect(Collectors.toList());
        getChild(menuTreeList, roleMenus);
        return menuTreeList;
    }

    /**
     * 递归查询所有的子菜单
     *
     * @param topList          顶级菜单
     * @param distinctMenuList 所有菜单
     * @return
     */
    public void getChild(List<MenuTreeDto> topList, List<Menu> distinctMenuList) {
        topList.forEach(t -> {
                    List<MenuTreeDto> childList = distinctMenuList.stream().filter(
                            menu -> menu.getPid().equals(t.getId()))
                            .map(menu -> MenuAssembler.assembleTreeDto(menu)).collect(Collectors.toList());
                    t.setChilds(childList);
                    if (childList.size() > 0) {
                        getChild(childList, distinctMenuList);
                    }
                }
        );
    }


    /**
     * 查询用户对应的菜单列表
     *
     * @param shopIdCmd
     * @return
     */
    public List<MenuShopDto> queryMenuByShop(ShopIdCmd shopIdCmd) {
        Long userId = UserIdUtils.getUserId();
        List<Menu> menuList = menuRepo.queryMenuByShop(shopIdCmd.getShopId(), userId);
        //去重复
        List<Menu> distinctMenuList = menuList.stream().distinct().collect(Collectors.toList());


        List<MenuShopDto> list = new ArrayList<>();
        String actions = null;
        List<MenuShopButtonDto> actionEntitySet = new ArrayList<>();
        MenuShopDto menuShopDto;
        MenuShopButtonDto menuShopButtonDto = null;
        for (Menu menu : distinctMenuList) {
            menuShopDto = MenuShopDto.builder().permissionId(menu.getCode()).permissionName(menu.getName()).build();
            //按钮
            List<Menu> btnList = distinctMenuList.stream().filter(btnmenu -> btnmenu.getMenuType() == 3 && menu.getMenuId().getId() == btnmenu.getPid()).collect(Collectors.toList());
            for (Menu btnMenu : btnList) {
                menuShopButtonDto = MenuShopButtonDto.builder()
                        .action(btnMenu.getCode())
                        .describe(btnMenu.getName())
                        .defaultCheck(false)
                        .build();
                actionEntitySet.add(menuShopButtonDto);
            }
            if (actionEntitySet.size() > 0) {
                actions = JSON.toJSONString(actionEntitySet);
            }
            menuShopDto.setActions(actions);
            menuShopDto.setActionEntitySet(actionEntitySet);
            list.add(menuShopDto);
        }
        return list;
    }

}
