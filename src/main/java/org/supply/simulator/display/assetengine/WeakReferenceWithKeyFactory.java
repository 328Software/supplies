package org.supply.simulator.display.assetengine;


import java.lang.ref.ReferenceQueue;

/**
 * Created by Brandon on 2/10/2018.
 */
@FunctionalInterface
public interface WeakReferenceWithKeyFactory {
    <K,V> WeakReferenceWithKey<K,V> get(K key, V value, ReferenceQueue<V> queue);
}
