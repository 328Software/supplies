package org.supply.simulator.display.assetengine.indices;

import org.supply.simulator.display.assetengine.AssetHandle;

/**
 * Created by Alex on 9/11/2014.
 */
public interface ChunkHandle extends AssetHandle {

    /**
     *
     * @return
     */
    public Integer getIndexId();

    /**
     *
     * @param indexId
     */
    public void setIndexId(Integer indexId);
}
