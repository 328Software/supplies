package org.supply.simulator;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;

/**
 * Created by Brandon on 5/12/2016.
 */
public class DummyTest {


    @Test
    public void test() {


        CacheManager manager = CacheManager.create();

        Cache testCache = manager.getCache("sampleCache1");

        System.out.println(testCache.getCacheConfiguration());
    }

}
