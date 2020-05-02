package com.jinshuo.core.Cache;

import java.util.Optional;

/**
 * @Classname Cache
 * @Description TODO
 * @Date 2019/7/10 14:15
 * @Created by dyh
 */
public interface Cache<K,V> {
    Boolean supports(Class<?> clazz);
    void put(K key, V value);
    Optional<V> get(K key);
    void invalidate(K key);
    boolean printStat();
}
