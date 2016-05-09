package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.attribute.entity.EntityType;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 2/13/14
 * Time: 12:09 AM
 * Entity is a representation of a physical object. It's subtypes describe "real", concrete
 * object matter that can be described by Attributes. Entity is meant to be used as the highest
 * non-generic super-type for all matter.
 */
public interface Entity<I extends Comparable<I>> extends  HasId<I>, Comparable<Entity<I>> {

    public EntityType getType();

    public void setType(EntityType type);

    @Override
    default int compareTo(Entity<I> o) {
        return getId().compareTo(o.getId());
    }
}
