package org.supply.simulator.display.assetengine.indices.impl;

import org.supply.simulator.display.assetengine.indices.ChunkIndexData;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicChunkIndexData<V extends Buffer> implements ChunkIndexData<IntBuffer> {
    private IntBuffer data;

    @Override
    public void setData(IntBuffer data) {
        this.data = data;
    }

    @Override
    public IntBuffer getData() {
        return data;
    }
}
