package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.HasName;
import org.supply.simulator.data.entity.AbstractUnit;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;

import javax.naming.Name;
import java.util.Set;

/**
 *  Node, used to represent towns
 */
public class BasicNode extends AbstractUnit implements Node, HasName<String> {


    private String name;

    private Set<Positions> p;

    public Set<Positions> getPositions() {
        return p;
    }

    public void setPositions(Set<Positions> p) {
        this.p = p;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
