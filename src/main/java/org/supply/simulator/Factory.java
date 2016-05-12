package org.supply.simulator;

import org.supply.simulator.data.entity.Positions;

/**
 * Created by Brandon on 5/8/2016.
 */
public interface Factory<I> {
    I build();
}
