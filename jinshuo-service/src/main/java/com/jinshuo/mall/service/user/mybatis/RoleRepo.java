package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.role.Role;
import com.jinshuo.mall.domain.user.model.role.RoleId;
import com.jinshuo.mall.service.user.mybatis.mapper.RoleMapper;
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
public class RoleRepo {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    /**
     * 获取id
     *
     * @return
     */
    public RoleId nextId() {
        return new RoleId(CommonSelfIdGenerator.generateId());
    }

    /**
     * 查询角色列表
     *
     * @param id
     * @return
     */
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }


    /**
     * 查询角色列表
     *
     * @param role
     * @return
     */
    public List<Role> findAll(Role role) {
        return roleMapper.queryRole(role);
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    public int save(Role role) {
        role.preInsert();
        //role.setStatus(Status.NORMAL);
        return roleMapper.save(role);
    }


    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    public int update(Role role) {
        return roleMapper.update(role);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return roleMapper.delete(id);
    }


    /**
     * 新增用户店铺角色中间表数据
     *
     * @param roles
     * @param userId
     */
    public void saveUserShopRole(List<Long> roles, Long userId) {
        roleMapper.saveUserShopRole(roles, userId);
    }


    /**
     * 根据用户id获取用户的角色列表
     *
     * @param userId
     * @return
     */
    public List<Long> getUserRoleIdList(Long userId) {
        return roleMapper.getUserRoleIdList(userId);
    }

    /**
     * 删除用户角色中间表数据
     *
     * @param roleIds
     * @param userId
     */
    public void deleteUserRole(List<Long> roleIds, Long userId) {
        roleMapper.deleteUserRole(roleIds, userId);
    }

    /**
     * 根据用户id获取用户的菜单列表
     *
     * @param userId
     * @return
     */
    public List<Long> getUserMenuIdList(Long userId, Long shopId) {
        return roleMapper.getUserMenuIdList(userId, shopId);
    }

    /**
     * 新增用户店铺菜单中间表数据
     *
     * @param menus
     * @param userId
     * @param shopId
     */
    public void saveUserShopMenu(List<Long> menus, Long userId, Long shopId) {
        roleMapper.saveUserShopMenu(menus, userId, shopId);
    }


    /**
     * 删除用户权限菜单中间表数据
     *
     * @param roleIds
     * @param userId
     */
    public void deleteUserMenu(List<Long> roleIds, Long userId, Long shopId) {
        roleMapper.deleteUserMenu(roleIds, userId, shopId);
    }

    /**
     * 保存角色菜单中间表
     *
     * @param menuIds
     * @param roleId
     */
    public int saveRoleMenu(List<Long> menuIds, Long roleId) {
        return roleMapper.saveRoleMenu(menuIds, roleId);
    }

    /**
     * 删除角色菜单中间表数据
     *
     * @param menuIds
     * @param roleId
     */
    public void deleteRoleMenu(List<Long> menuIds, Long roleId) {
        roleMapper.deleteRoleMenu(menuIds, roleId);
    }
}
