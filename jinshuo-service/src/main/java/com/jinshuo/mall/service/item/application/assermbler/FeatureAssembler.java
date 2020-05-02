package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.feature.Feature;
import com.jinshuo.mall.service.item.application.dto.FeatureDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by dyh on 2019/7/22.
 */
public class FeatureAssembler {

    /**
     * @param feature
     * @return
     */
    public static FeatureDto assembleDto(Feature feature) {
        if (null == feature) {
            return null;
        }
        FeatureDto dto = new FeatureDto();
        BeanUtils.copyProperties(feature, dto);
        dto.setId(feature.getFeatureId().getId().toString());
        if (null != feature.getShopId()) {
            dto.setShopId(feature.getShopId().toString());
        }
        return dto;
    }
}
