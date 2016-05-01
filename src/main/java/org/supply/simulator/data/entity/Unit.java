package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.UnitPositions;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Unit extends Entity<EntityAttribute,EntityStatistic,Long> {

    public UnitType getType();
    public void setType(UnitType unitType);

    public UnitPositions getUnitPositions();

    public void setUnitPositions(UnitPositions unitPositions);

}
