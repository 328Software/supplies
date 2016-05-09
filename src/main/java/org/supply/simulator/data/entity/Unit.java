package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Unit extends Entity<Long> {

//    @Override
//    public UnitType getType();
//
//    public void setType(UnitType type);

    public Positions getPositions();

    public void setPositions(Positions unitPositions);

}
