package org.supply.simulator.data.statistic;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/9/14
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface HasStatistics<S extends Statistic> {

    void addStatistic(S statistic);

    void removeStatistic(Object statisticId);

    S getStatistic(Object statisticId);

    boolean hasStatistic(Object statisticId);
}
