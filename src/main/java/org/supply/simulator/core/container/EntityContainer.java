package org.supply.simulator.core.container;

import org.supply.simulator.data.entity.Entity;

import java.util.Collection;

public interface EntityContainer {

    Collection<Entity> getAll();

    void add(Entity e);
}


