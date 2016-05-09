package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Colors<I extends Comparable<I>> extends HasId<I>, HasValue<byte[]>, Comparable<Colors<I>> {


    @Override
    default int compareTo(Colors<I> o) {
        return getId().compareTo(o.getId());
    }
}
