package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.HasName;
import org.supply.simulator.data.entity.AbstractUnit;
import org.supply.simulator.data.entity.Node;

import javax.naming.Name;

/**
 *  Node, used to represent towns
 */
public class BasicNode extends AbstractUnit implements Node, HasName {


    private String name;

    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
