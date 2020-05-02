package com.jinshuo.core.event;

import java.util.Date;

/**
 * @Classname DomainEvent
 * @Description TODO
 * @Date 2019/6/28 20:18
 * @Created by dyh
 */
public interface  DomainEvent {
    default int getEventVersion(){
        return eventVersion();
    }

    default Date getOccurredOn(){
        return occurredOn();
    }

    default int eventVersion() {
        return getEventVersion();
    }

    default Date occurredOn() {
        return getOccurredOn();
    }
}
