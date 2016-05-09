package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.Positions;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicPositions implements Positions<Long> {
    private Long id;
    
    private float[] value;

    @Override
    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


