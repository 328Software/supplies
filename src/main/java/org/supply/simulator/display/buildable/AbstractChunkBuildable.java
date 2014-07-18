package org.supply.simulator.display.buildable;

import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/17/2014.
 */
public abstract class AbstractChunkBuildable extends HasLogger implements SupplyBuildable {
    protected int[] locations;

    @Override
    public void setAttributeLocations(int[] locations) {
        this.locations = locations;
    }

    @Override
    public int[] getAttributeLocations () {
        return this.locations;

    }
}
