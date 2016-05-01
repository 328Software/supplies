package org.supply.simulator.data.statistic;

import org.supply.simulator.data.HasId;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/9/14
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Statistic<V,I> extends HasId<I> {
    V getValue();
}
