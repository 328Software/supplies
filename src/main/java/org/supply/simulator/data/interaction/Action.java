package org.supply.simulator.data.interaction;

import org.supply.simulator.data.attribute.interaction.ActionAttribute;
import org.supply.simulator.data.statistic.interaction.ActionStatistic;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/14/14
 * Time: 12:23 AM
 * An Action is a representation of information about how one impl will
 * alter the state of another impl.
 */
public interface Action<A extends ActionAttribute,S extends ActionStatistic,I> extends Interaction<A,S,I> {

}
