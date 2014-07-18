package org.supply.simulator.display.assetengine.indices;

import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * Created by Alex on 7/7/2014.
 */
public interface ChunkIndexEngine<K> extends AssetEngine<K,Integer> {
    @Override
    public Integer get (K type);

}
