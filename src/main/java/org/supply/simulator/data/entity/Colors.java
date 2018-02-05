package org.supply.simulator.data.entity;

import org.supply.simulator.data.HasId;
import org.supply.simulator.data.HasValue;

/**
 * Created by Alex on 9/14/2014.
 */
public class Colors implements HasId<Colors, Long>, HasValue<byte[]> {
    private Long id;

    private byte[] value;



    @Override
    public byte[] getValue() {
        return value;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
