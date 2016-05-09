package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.EntityType;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Menu extends Entity<Long> {

    @Override
    default EntityType getType() {
        return EntityType.MENU;
    }

    public Positions getPositions();

    public void setPositions(Positions positions);


}
