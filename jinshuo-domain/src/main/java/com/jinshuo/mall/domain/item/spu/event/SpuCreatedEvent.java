package com.jinshuo.mall.domain.item.spu.event;

import com.jinshuo.core.event.BaseDomainEvent;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Classname SpuCreatedEvent
 * @Description TODO
 * @Date 2019/6/28 21:03
 * @Created by dyh
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SpuCreatedEvent extends BaseDomainEvent {
    private SpuId spuId;
    private String name;
}
