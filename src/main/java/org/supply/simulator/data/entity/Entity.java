package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.attribute.HasAttributes;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.statistic.HasStatistics;
import org.supply.simulator.data.statistic.entity.EntityStatistic;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 2/13/14
 * Time: 12:09 AM
 * Entity is a representation of a physical object. It's subtypes describe "real", concrete
 * object matter that can be described by Attributes. Entity is meant to be used as the highest
 * non-generic super-type for all matter.
 */
public interface Entity
        <
        A extends EntityAttribute, //
        S extends EntityStatistic, //
        I                          //
        > extends HasAttributes<A>, HasStatistics<S>, HasId<I>, Comparable {

    public EntityType getType();

    public void setType(EntityType type);

}