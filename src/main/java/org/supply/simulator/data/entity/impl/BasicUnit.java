package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnit implements Unit {
    private Long id;
    private Positions unitPositions;
    private UnitType type;



    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public void setType(EntityType type) {
        this.type = (UnitType) type;
    }

    public Positions getPositions() {
        return unitPositions;
    }

    public void setPositions(Positions unitPositions) {
        this.unitPositions = unitPositions;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
