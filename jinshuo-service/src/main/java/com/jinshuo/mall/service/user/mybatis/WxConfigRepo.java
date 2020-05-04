package com.jinshuo.mall.service.user.mybatis;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.mall.domain.user.model.wx.WxConfig;
import com.jinshuo.mall.domain.user.model.wx.WxConfigId;
import com.jinshuo.mall.service.user.mybatis.mapper.WxConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Classname GoodsOrderRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dongyh
 */
@Repository
public class WxConfigRepo {


    @Autowired(required = false)
    private WxConfigMapper mapper;

    public WxConfigId nextId() {
        return new WxConfigId(CommonSelfIdGenerator.generateId());
    }

    public void save(WxConfig wxConfig) {
        mapper.save(wxConfig);
    }

    public WxConfig findByShopIdAndType(Long shopId, Integer type) {
        return mapper.findByShopIdAndType(shopId, type);
    }

}
