package org.supply.simulator.display.assetengine;

/**
 * Created by Alex on 9/11/2014.
 */
public class AbstractAssetHandle implements AssetHandle {
    protected Integer count=0;

    @Override
    public void add() {
        count = count +1;
    }

    @Override
    public void subtract() {
        count = count -1;
    }

    @Override
    public Integer count() {
        return count;
    }
}
