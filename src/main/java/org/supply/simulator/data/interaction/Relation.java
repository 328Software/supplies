package org.supply.simulator.data.interaction;

import org.supply.simulator.data.attribute.interaction.RelationAttribute;
import org.supply.simulator.data.statistic.interaction.RelationStatistic;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/14/14
 * Time: 12:23 AM
 * A Relation is a representation of an impl's disposition towards another impl.
 */
public interface Relation<A extends RelationAttribute, S extends RelationStatistic,I> extends Interaction<A,S,I> {

}
