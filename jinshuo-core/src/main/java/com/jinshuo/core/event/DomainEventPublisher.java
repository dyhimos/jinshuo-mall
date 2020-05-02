package com.jinshuo.core.event;

/**
 * @Classname DomainEventPublisher
 * @Description 领域事件发布器
 * @Date 2019/6/28 19:53
 * @Created by dyh
 */
public class DomainEventPublisher<T extends BaseDomainEvent> {


    static <T> void  register(Object listener){};

    static <T> void  publish(T event){};

    static <T> void  asyncPublish(T event){};
}
