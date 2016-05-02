package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.UnitPositions;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Menu extends Entity<EntityAttribute,EntityStatistic,Long>, Comparable {

//    @Override
//    public MenuType getType();

//    public void setType(MenuType unitType);

    public UnitPositions getPositions();

    public void setPositions(UnitPositions unitPositions);


}
