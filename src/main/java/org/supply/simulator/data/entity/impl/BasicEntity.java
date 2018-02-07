package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.Attribute;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;

import java.util.Set;

/**
 * Created by Brandon on 2/7/2018.
 */
public class BasicEntity implements Entity {
    private Set<Positions> positions;
    private Long id;

    @Override
    public Set<Positions> getPositions() {
        return positions;
    }

    @Override
    public void setPositions(Set<Positions> positions) {
        this.positions = positions;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
