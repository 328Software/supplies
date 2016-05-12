package org.supply.simulator.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import static java.util.Objects.isNull;

/**
 * Created by Brandon on 5/12/2016.
 */
public class Cache<K, V> {
    net.sf.ehcache.Cache cache;

    public Cache(String name) {
        CacheManager manager = CacheManager.create();

        this.cache = manager.getCache(name);
        if(isNull(this.cache)) {
            throw new RuntimeException("Could not configure cache");
        }
    }

    V get(K key) {
        return (V) cache.get(key);
    }

    void put(K key, V value) {
        cache.put(new Element(key, value));
    }
}
