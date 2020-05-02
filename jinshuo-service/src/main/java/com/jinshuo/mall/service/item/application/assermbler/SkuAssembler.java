package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.sku.Sku;
import com.jinshuo.mall.service.item.application.dto.SkuDto;
import com.jinshuo.mall.service.item.application.dto.UserSkuDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/19.
 */
public class SkuAssembler {
    /**
     * @param sku
     * @return
     */
    public static SkuDto assembleSkuDto(Sku sku) {
        if (sku == null) {
            return null;
        }
        SkuDto skuDto = new SkuDto();
        BeanUtils.copyProperties(sku, skuDto);
        skuDto.setId(sku.getSkuId().getId().toString());
        skuDto.setSpuId(sku.getSpuId().getId().toString());
        return skuDto;
    }


    /**
     * 转化为前端DTO
     *
     * @param sku
     * @return skuDto
     */
    public static UserSkuDto assembleUserSkuDto(Sku sku) {
        if (sku == null) {
            return null;
        }
        UserSkuDto userSkuDto = new UserSkuDto();
        BeanUtils.copyProperties(sku, userSkuDto);
        userSkuDto.setId(sku.getSkuId().getId().toString());
        userSkuDto.setSpuId(sku.getSpuId().getId().toString());
        return userSkuDto;
    }
}
