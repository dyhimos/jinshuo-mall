package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import java.util.List;

/**
 * Created by 19458 on 2019/10/10.
 */
@Data
public class PriceCalendarMainCmd {
    private Long skuId;
    private List<PriceCalendarCmd> list;
}
