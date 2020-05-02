package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSON;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.domain.user.model.role.Role;
import com.jinshuo.mall.domain.user.model.role.RoleId;
import com.jinshuo.mall.domain.user.model.shop.ShopId;
import com.jinshuo.mall.service.user.application.cmd.RoleCmd;
import com.jinshuo.mall.service.user.mybatis.MenuRepo;
import com.jinshuo.mall.service.user.mybatis.MerchantMenuRepo;
import com.jinshuo.mall.service.user.mybatis.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Description: 角色service
 * @Author: dongyh
 * @CreateDate: 2019/11/4 10:28
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/11/4 10:28
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@Service
public class RoleCmdService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private MerchantMenuRepo merchantMenuRepo;

    /**
     * 保存/更新角色
     *
     * @param roleCmd
     */
    //@Transactional
    public void save(RoleCmd roleCmd) {
        Role role = Role.builder()
                .shopId(new ShopId(roleCmd.getShopId()))
                .code(roleCmd.getCode())
                .name(roleCmd.getName())
                .build();
        if (roleCmd.getId() != null) {
            Long roleId = roleCmd.getId();
            //查询是否存在
            Role roleCheck = roleRepo.findById(roleId);
            if (roleCheck == null) {
                throw new UcBizException(UcReturnCode.UC200031.getMsg(), UcReturnCode.UC200031.getCode());
            }
            role.setRoleId(new RoleId(roleId));
            roleRepo.update(role);

            //更新角色和菜单中间表
            upRoleMenu(roleCmd);
        } else {
            role.preInsert();
            role.setRoleId(roleRepo.nextId());
            roleRepo.save(role);
        }
    }

    /**
     * 更新角色和菜单之间的关系
     *
     * @param roleCmd
     */
    private void upRoleMenu(RoleCmd roleCmd) {
        Long roleId = roleCmd.getId();

        //从数据库中拿到已经存在的菜单
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<Menu> exitMenu = menuRepo.queryMenuByRoleId(roleIds);
        List<Long> exitMenuIds = exitMenu.stream().map(menu -> menu.getMenuId().getId()).collect(Collectors.toList());

        //新提交的菜单列表
        List<Long> commitMenuIdList = roleCmd.getMenuIdList();

        //角色删除的菜单
        List<Long> deleteMenuList = exitMenuIds.stream().filter(item -> !commitMenuIdList.contains(item)).collect(toList());
        log.info("角色删除的菜单：{}", JSON.toJSONString(deleteMenuList));

        // 角色新增的菜单
        List<Long> addMenuList = commitMenuIdList.stream().filter(item -> !exitMenuIds.contains(item)).collect(toList());
        log.info("角色新增的菜单：{}", JSON.toJSONString(addMenuList));

        if (addMenuList.size() > 0) {
            //保存新增的
            roleRepo.saveRoleMenu(addMenuList, roleId);
        }
        if (deleteMenuList.size() > 0) {
            //删除不存在的
            roleRepo.deleteRoleMenu(deleteMenuList, roleId);
        }

        /*//从数据库中拿到已经存在的菜单
        List<MerchantMenu> exitMerchantMenuList = merchantMenuRepo.findByRoleId(roleId);
        //新提交的菜单列表
        List<Long> commitMenuIdList = roleCmd.getMenuIdList();

        //数据库中的MeunIdList
        List<Long> dbMenuIdList = null;
        //已存在的MeunIdList
        List<Long> exitMenuIdList = null;

        for(MerchantMenu merchantMenu : exitMerchantMenuList){
            Long menuId = merchantMenu.getMenuId();
            if(commitMenuIdList.contains(menuId)){
                exitMenuIdList.add(menuId);
            }
            dbMenuIdList.add(menuId);
        }

        //新增的MeunIdList
        commitMenuIdList.removeAll(exitMenuIdList);
        //删除的MeunIdList
        dbMenuIdList.remove(exitMenuIdList);


        List<MerchantMenu> list = new ArrayList<>();
        MerchantMenu merchantMenu =null;
        for(Long menuId : commitMenuIdList){
            merchantMenu = new MerchantMenu();
            merchantMenu.setMenuId(menuId);
            merchantMenu.setRoleId(roleId);
            list.add(merchantMenu);
        }
        //保存新增的
        merchantMenuRepo.saveMerchantMenu(list);
        //删除不存在的
        merchantMenuRepo.deleteMerchantMenu(dbMenuIdList,roleId);*/
    }


    /**
     * 删除角色
     *
     * @param roleCmd
     */
    public int delete(RoleCmd roleCmd) {
        return roleRepo.delete(roleCmd.getId());
    }
}
