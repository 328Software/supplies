package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.entity.Positions;

import java.util.Iterator;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenu extends AbstractEntityType implements Menu {

    private Positions unitPositions;
    private MenuType type;

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public void setType(EntityType type) {
        this.type = (MenuType) type;
    }

    public Positions getPositions() {
        return unitPositions;
    }

    public void setPositions(Positions unitPositions) {
        this.unitPositions = unitPositions;
    }
}
