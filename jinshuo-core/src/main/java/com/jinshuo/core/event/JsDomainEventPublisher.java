package com.jinshuo.core.event;

/**
 * @Classname JsDomainEventPublisher
 * @Description 领域事件发布器
 * @Date 2019/6/29 11:30
 * @Created by dyh
 */
public abstract class JsDomainEventPublisher extends DomainEventPublisher{
 /*   private EventBus syncBus = new EventBus(identify());
    private EventBus asyncBus = new AsyncEventBus(identify(), Executors.newFixedThreadPool(1));

    @Override
    public void register(Object listener) {
        syncBus.register(listener);
        asyncBus.register(listener);
    }

    @Override
    public void publish(DomainEvent event) {
        syncBus.post(event);
    }

    @Override
    public void asyncPublish(DomainEvent event) {
        asyncBus.post(event);
    }*/
}
