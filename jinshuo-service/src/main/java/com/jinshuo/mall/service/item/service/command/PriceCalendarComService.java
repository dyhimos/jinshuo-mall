package com.jinshuo.mall.service.item.service.command;

import com.jinshuo.mall.domain.item.price.PriceCalendar;
import com.jinshuo.mall.service.item.application.cmd.PriceCalendarCmd;
import com.jinshuo.mall.service.item.application.cmd.PriceCalendarMainCmd;
import com.jinshuo.mall.service.item.mybatis.PriceCalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Service
public class PriceCalendarComService {

    @Autowired
    private PriceCalendarRepo priceCalendarRepo;


    /**
     * 保存价格日历
     *
     * @param cmd
     */
    public int create(PriceCalendarMainCmd cmd) {
        List<PriceCalendar> list = new ArrayList<>();
        PriceCalendar priceCalendar;
        for (PriceCalendarCmd calendarCmd : cmd.getList()) {
            priceCalendar = new PriceCalendar();
            priceCalendar.build(cmd.getSkuId(), calendarCmd.getDate(), calendarCmd.getPrice(), calendarCmd.getQuantity());
            list.add(priceCalendar);
        }
        priceCalendarRepo.deleteBySkuId(cmd.getSkuId());
        return priceCalendarRepo.save(list);
    }


}
