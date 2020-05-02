package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.shop.Shop;
import com.jinshuo.mall.service.user.application.dto.ShopDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname GoodsOrderAssembler
 * @Description GoodsOrderAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/9/17 18:27
 * @Created by dyh
 */
@Component
public class ShopAssembler {

    /**
     * 返回订单列表
     *
     * @param shop
     * @return
     */
    public static ShopDto assembleShopDto(Shop shop) {
        ShopDto dto = new ShopDto();
        if (shop == null) {
            return null;
        }
        BeanUtils.copyProperties(shop, dto);
        dto.setId(shop.getShopId().getId().toString());
        return dto;
    }
}
