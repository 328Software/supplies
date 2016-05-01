package org.supply.simulator.data.statistic.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface UnitPositions extends EntityStatistic<float[],Long>{

    public float[] getValue();

    public void setValue(float[] value);
}
