package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.entity.Colors;

/**
 * Created by Alex on 9/7/2014.
 */
public class MockColors implements Colors<Long> {
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
