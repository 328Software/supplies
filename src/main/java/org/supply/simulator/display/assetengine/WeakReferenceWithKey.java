package org.supply.simulator.display.assetengine;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Brandon on 2/10/2018.
 */
public class WeakReferenceWithKey<K, V> extends WeakReference<V> {
    final K key;

    public WeakReferenceWithKey(K key, V handle, ReferenceQueue<? super V> q) {
        super(handle, q);
        this.key = key;
    }

    public K getKey() {
        return key;
    }
}
