package org.supply.simulator.display.assetengine;

import java.util.Map;

/**
 * Created by Alex on 7/13/2014.
 */
public interface AssetEngine<K,V> {

    /**
     *
     * @param key
     * @return
     */
    public V get(K key);

}
