package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;

/**
 * Created by Alex on 9/14/2014.
 */
public class Positions implements HasId<Long>, HasValue<float[]> {
    private Long id;

    private float[] value;

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

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
