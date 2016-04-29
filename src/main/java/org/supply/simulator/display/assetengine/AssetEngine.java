package org.supply.simulator.display.assetengine;


/**
 * Created by Alex on 7/13/2014.
 */
public interface AssetEngine<K,V extends AssetHandle> {
    /**
     *
     * @param key
     * @return
     */
    public V get(K key);

    public void done(K key);


}
