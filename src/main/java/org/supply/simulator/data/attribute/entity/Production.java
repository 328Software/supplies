package org.supply.simulator.data.attribute.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;
import org.supply.simulator.data.attribute.Attribute;

/**
 * Supply production attribute
 *    how much supplies an entity is producing
 */
public class Production implements Attribute<Long>, HasValue<Float> {
    private Long id;

    private float value;

    private String source;

    public void add(float addedValue) {
     this.value=value+addedValue;
    }

    public void set(float value) {
        this.value=value;
    }

    public String getSource() {
        return source;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Float getValue() {
        return value;
    }
}
