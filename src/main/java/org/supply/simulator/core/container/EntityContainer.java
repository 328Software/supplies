package org.supply.simulator.core.container;

import org.supply.simulator.data.entity.Entity;

import java.util.Collection;

public interface EntityContainer<E extends Entity> {

    Collection<E> getAll();

    void add(E e);
}


