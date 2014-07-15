package org.supply.simulator.display.assetengine.indices.impl;

import org.supply.simulator.display.assetengine.indices.ChunkIndexHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicChunkIndexHandle implements ChunkIndexHandle {
    private Integer indicesId;
    @Override
    public Integer getIndicesId() {
        return this.indicesId;
    }

    @Override
    public void setIndicesId(Integer indicesId) {
        this.indicesId =indicesId;

    }
}
