package org.supply.simulator.data.attribute;

import org.supply.simulator.data.HasId;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/14/14
 * Time: 12:23 AM
 *
 * The superclass for attributes of all types of things (entities and interactions)
 * that exist in the game. Attributes are supposed to describe every single detail
 * of an object.
 */
public interface Attribute<I extends Comparable<I>> extends HasId<I> {

}
