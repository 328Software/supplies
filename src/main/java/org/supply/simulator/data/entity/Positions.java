package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Positions<I extends Comparable<I>> extends HasId<I> {
    float[] getValue();
}
