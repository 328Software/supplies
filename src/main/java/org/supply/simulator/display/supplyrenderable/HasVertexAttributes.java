package org.supply.simulator.display.supplyrenderable;

/**
 * Created by Alex on 6/19/2014.
 */
public interface HasVertexAttributes {

    /**
     *
     * @param locations
     */
    void setAttributeLocations(int[] locations);

    /**
     *
     * @return
     */
    int[] getAttributeLocations ();
}