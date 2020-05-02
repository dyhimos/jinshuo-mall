package com.jinshuo.mall.service.item.application.assermbler;

import com.jinshuo.mall.domain.item.price.PriceCalendar;
import com.jinshuo.mall.service.item.application.dto.PriceCalendarDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/7/22.
 */
public class PriceCalendarAssembler {

    /**
     * @param priceCalendar
     * @return
     */
    public static PriceCalendarDto assembleDto(PriceCalendar priceCalendar) {
        if (priceCalendar == null) {
            return null;
        }
        PriceCalendarDto dto = new PriceCalendarDto();
        BeanUtils.copyProperties(priceCalendar, dto);
        return dto;
    }
}
