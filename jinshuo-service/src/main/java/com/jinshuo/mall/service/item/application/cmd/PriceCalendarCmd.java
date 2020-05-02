package com.jinshuo.mall.service.item.application.cmd;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 19458 on 2019/10/10.
 */
@Data
public class PriceCalendarCmd {
    private Date date;
    private Integer quantity;
    private BigDecimal price;
}
