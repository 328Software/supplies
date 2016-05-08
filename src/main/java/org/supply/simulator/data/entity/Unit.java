package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.Positions;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Unit extends Entity<EntityAttribute,EntityStatistic,Long> {

//    @Override
//    public UnitType getType();
//
//    public void setType(UnitType type);

    public Positions getPositions();

    public void setPositions(Positions unitPositions);

}
