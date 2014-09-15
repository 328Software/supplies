package org.supply.simulator.data.statistic.entity.impl;

import org.supply.simulator.data.statistic.entity.ChunkPositions;
import org.supply.simulator.data.statistic.entity.EntityStatistic;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunkPositions implements ChunkPositions {
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


