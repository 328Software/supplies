package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.Positions;

import java.util.Iterator;

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

    @Override
    public Iterator<EntityAttribute> iterator() {
        return null;
    }

    @Override
    public void addAttribute(EntityAttribute attribute) {

    }

    @Override
    public void removeAttribute(Object attributeId) {

    }

    @Override
    public EntityAttribute getAttribute(Object attributeId) {
        return null;
    }

    @Override
    public boolean hasAttribute(Object attributeId) {
        return false;
    }

    @Override
    public void addStatistic(EntityStatistic statistic) {

    }

    @Override
    public void removeStatistic(Object statisticId) {

    }

    @Override
    public EntityStatistic getStatistic(Object statisticId) {
        return null;
    }

    @Override
    public boolean hasStatistic(Object statisticId) {
        return false;
    }

    @Override
    public int compareTo (Object o) {
        if (this == o) return 0;
        if (!(o instanceof Unit)) return -1;

        return this.type.getTextureHandle().compareTo(((Unit) o).getType().getTextureHandle());


    }
}
