package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.brand.Brand;
import com.jinshuo.mall.service.item.application.dto.BrandDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class BrandAssembler {

    /**
     * @param brand
     * @return
     */
    public static BrandDto assembleBrandDto(Brand brand) {
        if (brand == null) {
            return null;
        }
        BrandDto dto = new BrandDto();
        BeanUtils.copyProperties(brand, dto);
        dto.setId(brand.getBrandId().getId().toString());
        return dto;
    }
}
