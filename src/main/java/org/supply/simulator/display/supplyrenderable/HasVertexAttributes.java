package org.supply.simulator.display.supplyrenderable;

/**
 * Interface for vertex attribute data for SupplyRenderable objects.
 *
 * Created by Alex on 6/19/2014.
 */
public interface HasVertexAttributes {

    /**
     * Setter.
     *
     * @param locations
     */
    void setAttributeLocations(int[] locations);

    /**
     * Getter.
     *
     * @return
     */
    int[] getAttributeLocations ();
}
