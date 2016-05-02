package org.supply.simulator.badengine;

/**
 * Created by Brandon on 7/20/2014.
 */
public interface Partitionable<V> {
    Partitions<V> partition();
}
