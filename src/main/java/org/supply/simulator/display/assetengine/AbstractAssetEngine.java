package org.supply.simulator.display.assetengine;

import org.supply.simulator.logging.HasLogger;

import java.util.HashMap;

/**
 * Created by Alex on 9/11/2014.
 */
public abstract class AbstractAssetEngine<K,V extends AssetHandle> extends HasLogger implements AssetEngine<K,V> {
    protected HashMap<K,V> handleMap;
    protected HashMap<K,CountVar> countsMap;

    public AbstractAssetEngine() {
        handleMap = new HashMap<>();
        countsMap = new HashMap<>();
    }


    @Override
    public V get(K key) {

        if (!handleMap.containsKey(key)) {

            handleMap.put(key,createHandle(key));
        }
        V handle = handleMap.get(key);
        handle.add();
        return handle;
    }

    @Override
    public void done(K key) {
        if (handleMap.containsKey(key)) {
            V handle = handleMap.get(key);
            handle.subtract();

            if (handle.count()==0) {
                destroyHandle(key);
            }
        }
    }

    protected abstract void destroyHandle(K key);


    protected abstract V createHandle(K key);

    protected class CountVar {

        Integer count;

        public void add() {
            count = count + 1;
        }

        public void subtract() {
            count = count + 1;
        }

    }
}
