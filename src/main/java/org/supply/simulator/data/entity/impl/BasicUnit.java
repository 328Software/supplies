package org.supply.simulator.data.entity.impl;

import org.supply.simulator.core.container.AttributeContainer;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.AbstractUnit;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.Atlas;

import java.util.Set;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnit extends AbstractUnit implements Entity {

    private Set<Positions> p;

    public Set<Positions> getPositions() {
        return p;
    }

    public void setPositions(Set<Positions> p) {
        this.p = p;
    }

}
