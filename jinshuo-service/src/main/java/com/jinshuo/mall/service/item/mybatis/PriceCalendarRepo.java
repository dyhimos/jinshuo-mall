package com.jinshuo.mall.service.item.mybatis;

import com.jinshuo.mall.domain.item.price.PriceCalendar;
import com.jinshuo.mall.service.item.mybatis.mapper.PriceCalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname SpuRepo
 * @Description TODO
 * @Date 2019/6/16 20:07
 * @Created by dyh
 */
@Repository
public class PriceCalendarRepo {

    @Autowired(required = false)
    private PriceCalendarMapper priceCalendarMapper;

    public int save(List<PriceCalendar> priceCalendars) {
        return priceCalendarMapper.create(priceCalendars);
    }

    public List<PriceCalendar> queryBySkuId(Long skuId) {
        return priceCalendarMapper.queryBySkuId(skuId);
    }

    public int deleteBySkuId(Long skuId) {
        return priceCalendarMapper.deleteBySkuId(skuId);
    }

}
