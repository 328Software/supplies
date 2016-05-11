package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Unit extends Entity {
    Positions getPositions();

    void setPositions(Positions unitPositions);

}
