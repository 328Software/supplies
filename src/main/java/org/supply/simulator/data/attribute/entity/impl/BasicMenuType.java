package org.supply.simulator.data.attribute.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.MenuType;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenuType extends AbstractEntityType implements MenuType {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
