package org.supply.simulator.display.assetengine.indices.impl;

import org.supply.simulator.display.assetengine.indices.ChunkIndexData;

import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicChunkIndexData<V extends List<Integer>> implements ChunkIndexData<List<Integer>> {
    private List<Integer> data;

    @Override
    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public List<Integer> getData() {
        return data;
    }
}
