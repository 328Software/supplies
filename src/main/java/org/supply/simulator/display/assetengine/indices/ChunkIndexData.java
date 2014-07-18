package org.supply.simulator.display.assetengine.indices;

import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public interface ChunkIndexData<V> {

    void setData(List<Integer> data);

    public V getData();
}
