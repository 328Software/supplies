package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.UnitPositions;

import java.util.Iterator;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenu extends AbstractEntityType implements Menu {

    private UnitPositions unitPositions;
    private MenuType type;

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public void setType(EntityType type) {
        this.type = (MenuType) type;
    }

    public UnitPositions getPositions() {
        return unitPositions;
    }

    public void setPositions(UnitPositions unitPositions) {
        this.unitPositions = unitPositions;
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
        if (!(o instanceof Menu)) return -1;

        return this.getType().getTextureHandle().compareTo(((Menu) o).getType().getTextureHandle());


    }

}
