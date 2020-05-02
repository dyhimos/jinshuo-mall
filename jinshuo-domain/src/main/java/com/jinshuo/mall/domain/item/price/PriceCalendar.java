package com.jinshuo.mall.domain.item.price;

import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.model.IdentifiedEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dongyh on 2019/10/9.
 */
@Data
public class PriceCalendar extends IdentifiedEntity {
    private PriceCalendarId priceCalendarId;
    private Long skuId;
    private BigDecimal price;
    private Date date;
    private Integer quantity;

    public PriceCalendar build(Long skuId, Date date, BigDecimal price, Integer quantity) {
        this.priceCalendarId = new PriceCalendarId(CommonSelfIdGenerator.generateId());
        this.skuId = skuId;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        return this;
    }
}
