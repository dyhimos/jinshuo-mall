package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.Menu.Menu;
import com.jinshuo.mall.domain.user.model.Menu.MenuId;
import com.jinshuo.mall.service.user.mybatis.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class MenuRepo {

    @Autowired(required = false)
    private MenuMapper menuMapper;

    public MenuId nextId() {
        return new MenuId(CommonSelfIdGenerator.generateId());
    }

    public List<Menu> findAll(Menu menu) {
        return menuMapper.queryAllMenu(menu);
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    public int save(Menu menu) {
        return menuMapper.create(menu);
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    public int delete(Long id) {
        return menuMapper.delete(id);
    }

    /**
     * 根据功能id查询菜单
     * @param functionIds
     * @return
     */
    public List<Menu> queryMenuByFunctionId(List<Long> functionIds){
        return menuMapper.queryMenuByFunctionId(functionIds);
    }

    /**
     * 根据角色id查询菜单
     * @param roleIds 角色id集合
     * @return
     */
    public List<Menu> queryMenuByRoleId(List<Long> roleIds){
        return menuMapper.queryMenuByRoleId(roleIds);
    }


    /**
     * 根据店铺和角色查询菜单
     * @param shopId
     * @param userId
     * @return
     */
    public List<Menu> queryMenuByShop(Long shopId,Long userId){
        return menuMapper.queryMenuByShop(shopId,userId);
    }
}
