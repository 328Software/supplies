package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Menu extends Entity<Long> {

    Positions getPositions();

    void setPositions(Positions positions);


}
