package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.attribute.entity.impl.BasicUnitType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.UnitPositions;
import org.supply.simulator.data.statistic.entity.impl.BasicUnitPositions;

import java.util.Iterator;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicUnit implements Unit {
    private Long id;
    private UnitPositions unitPositions;
    private UnitType type;




    public UnitType getType() {
        return type;
    }

    public void setType(UnitType unitType) {
        type=unitType;
    }

    public UnitPositions getUnitPositions() {
        return unitPositions;
    }

    public void setUnitPositions(UnitPositions unitPositions) {
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
}
