package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.area.City;
import com.jinshuo.mall.service.item.application.dto.CityDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class CityAssembler {

    /**
     * @param city
     * @return
     */
    public static CityDto assembleDto(City city) {
        if (city == null) {
            return null;
        }
        CityDto dto = new CityDto();
        BeanUtils.copyProperties(city, dto);
        return dto;
    }
}
