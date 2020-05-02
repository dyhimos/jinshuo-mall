package com.jinshuo.mall.service.item.service.query;

import com.jinshuo.mall.service.item.application.assermbler.PriceCalendarAssembler;
import com.jinshuo.mall.service.item.application.dto.PriceCalendarDto;
import com.jinshuo.mall.service.item.application.dto.PriceCalendarMainDto;
import com.jinshuo.mall.service.item.mybatis.PriceCalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class PriceCalendarQueryService {

    @Autowired
    private PriceCalendarRepo priceCalendarRepo;

    /**
     * 根据skuId查询价格日历
     *
     * @param skuId
     * @return
     */
    public PriceCalendarMainDto getById(Long skuId) {
        List<PriceCalendarDto> resultList = priceCalendarRepo.queryBySkuId(skuId)
                .stream()
                .map(priceCalendar -> PriceCalendarAssembler.assembleDto(priceCalendar))
                .collect(Collectors.toList());
        PriceCalendarMainDto dto = new PriceCalendarMainDto();
        dto.setSkuId(skuId);
        dto.setList(resultList);
        return dto;
    }

}
