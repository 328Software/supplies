package org.supply.simulator.collections;

import org.supply.simulator.data.entity.Entity;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 3/2/14
 * Time: 1:13 PM
 * A custom collection for storing Entities.
 */
public interface EntitySet<E extends Entity> extends Set<E> {
}
