package org.supply.simulator.display.assetengine;


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

    @Deprecated
    default void done(K key) {
        //do nothing
    }


}
