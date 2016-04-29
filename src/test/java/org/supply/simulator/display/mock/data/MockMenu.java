package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.data.statistic.entity.EntityStatistic;
import org.supply.simulator.data.statistic.entity.UnitPositions;

import java.util.Iterator;

/**
 * Created by Alex on 9/14/2014.
 */
public class MockMenu implements Menu {
    private Long id;
    private UnitPositions unitPositions;
    private MenuType type;


    public MenuType getType() {
        return type;
    }

    public void setType(MenuType unitType) {
        type = unitType;
    }

    public UnitPositions getPositions() {
        return unitPositions;
    }

    public void setPositions(UnitPositions unitPositions) {
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
