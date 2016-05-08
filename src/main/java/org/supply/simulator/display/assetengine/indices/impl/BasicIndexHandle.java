package org.supply.simulator.display.assetengine.indices.impl;

import org.supply.simulator.display.assetengine.AbstractAssetHandle;
import org.supply.simulator.display.assetengine.indices.IndexHandle;

/**
 * Created by Alex on 9/11/2014.
 */
public class BasicIndexHandle extends AbstractAssetHandle implements IndexHandle {
    private Integer indexId;

    @Override
    public Integer getIndexId() {
        return indexId;
    }

    @Override
    public void setIndexId(Integer indexId) {
        this.indexId=indexId;

    }

}
