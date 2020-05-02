package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.mall.domain.user.model.shopMessageConfig.ShopMessageConfig;
import com.jinshuo.mall.service.user.mybatis.mapper.ShopMessageConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class ShopMessageConfigRepo {

    @Autowired(required = false)
    private ShopMessageConfigMapper mapper;

    /**
     * 保存短信配置信息
     *
     * @param shopMessageConfig
     * @return
     */
    public int save(ShopMessageConfig shopMessageConfig) {
        return mapper.create(shopMessageConfig);
    }

    /**
     * 更新短信配置信息
     *
     * @param shopMessageConfig
     * @return
     */
    public int update(ShopMessageConfig shopMessageConfig) {
        return mapper.update(shopMessageConfig);
    }

    /**
     * 根据id查询店铺短信信息
     *
     * @param id
     * @return
     */
    public ShopMessageConfig findById(Long id) {
        return mapper.queryById(id);
    }

    /**
     * 根据店铺id查询店铺短信息配置信息
     *
     * @param shopId
     * @return
     */
    public ShopMessageConfig queryByShopId(Long shopId) {
        return mapper.queryByShopId(shopId);
    }


    /**
     * 删除配置信息
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return mapper.delete(id);
    }

}
