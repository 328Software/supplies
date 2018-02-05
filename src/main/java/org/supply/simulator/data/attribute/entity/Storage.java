package org.supply.simulator.data.attribute.entity;

import org.supply.simulator.data.HasValue;
import org.supply.simulator.data.attribute.Attribute;

/**
 * Storage
 *  where
 */
public class Storage implements Attribute<Long>, HasValue<Float> {
    Long id;
    float value;

    String supplyType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Float getValue() {
        return value;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

}
