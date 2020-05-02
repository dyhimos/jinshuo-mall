package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.merchant.MerchantMenu;
import com.jinshuo.mall.service.user.mybatis.mapper.MerchantMenuMapper;
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
public class MerchantMenuRepo {

    @Autowired(required = false)
    private MerchantMenuMapper merchantMenuMapper;

    public List<MerchantMenu> findAll(Long merchantId) {
        return merchantMenuMapper.queryMyMenu(merchantId);
    }

    /**
     * 根据角色id查询菜单信息
     * @param roleId
     * @return
     */
    public List<MerchantMenu> findByRoleId(Long roleId) {
        return merchantMenuMapper.findByRoleId(roleId);
    }

    /**
     * 新增菜单数据
     * @param list
     */
    public void saveMerchantMenu(List<MerchantMenu> list){
        merchantMenuMapper.save(list);
    }

    /**
     * 删除菜单对应数据
     * @param
     */
    public void deleteMerchantMenu(List<Long> menuList,Long roleId){
        merchantMenuMapper.delete(menuList,roleId);
    }
}
