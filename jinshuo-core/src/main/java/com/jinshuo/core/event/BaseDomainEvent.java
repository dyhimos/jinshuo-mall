package com.jinshuo.core.event;

import lombok.Getter;

import java.util.Date;

/**
 * @Classname BaseDomainEvent
 * @Description TODO
 * @Date 2019/6/28 21:01
 * @Created by dyh
 */
public abstract class BaseDomainEvent implements DomainEvent{
    @Getter
    protected int eventVersion = 1;

    @Getter
    protected Date OccurredOn = new Date();
}
