package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.statistic.entity.UnitPositions;

/**
 * Created by Alex on 9/7/2014.
 */
public class MockUnitPositions implements UnitPositions {
    private float[] value;
    private Long id;



    @Override
    public float[] getValue() {
        return value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(float[] value) {
        this.value = value;
    }
}
