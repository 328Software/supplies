package org.supply.simulator.display.assetengine.indices;

import org.supply.simulator.display.assetengine.AssetEngine;

/**
 * Created by Alex on 7/7/2014.
 */
public interface IndexEngine<K> extends AssetEngine<K,IndexHandle> {
    @Override
    public IndexHandle get(K type);

}