package org.supply.simulator.data.attribute.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.UnitType;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnitType extends AbstractEntityType implements UnitType {

    private String name;



    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
