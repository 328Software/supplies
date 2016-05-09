package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Brandon on 5/8/2016.
 */
public class SimpleTextMenu extends AbstractEntityType implements Menu {
    String text;


    @Override
    public Positions getPositions() {
        return null;
    }

    @Override
    public void setPositions(Positions positions) {

    }

    @Override
    public EntityType getType() {
        return null;
    }

    @Override
    public void setType(EntityType type) {

    }
}
