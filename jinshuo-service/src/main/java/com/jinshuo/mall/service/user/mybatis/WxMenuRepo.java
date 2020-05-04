package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.wxMenu.WxMenu;
import com.jinshuo.mall.domain.user.model.wxMenu.WxMenuId;
import com.jinshuo.mall.service.user.mybatis.mapper.WxMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 微信菜单
 * @Classname WxMenuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by mgh
 */
@Repository
public class WxMenuRepo {


    @Autowired
    private WxMenuMapper mapper;


    public WxMenuId nextId() {
        return new WxMenuId(CommonSelfIdGenerator.generateId());
    }

    public void save(WxMenu wxMenu) {
        mapper.save(wxMenu);
    }


    /**
     * 根据店铺id查询菜单
     * @param shopId
     * @return
     */
    public List<WxMenu> findByShopId(Long shopId){
        return mapper.findByShopId(shopId);
    }

}
