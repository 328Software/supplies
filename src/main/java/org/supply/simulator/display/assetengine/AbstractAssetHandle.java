package org.supply.simulator.display.assetengine;

/**
 * Created by Alex on 9/11/2014.
 */
public class AbstractAssetHandle {
    protected Integer count=0;

    public void add() {
        count = count +1;
    }

    public void subtract() {
        count = count -1;
    }

    public Integer count() {
        return count;
    }
}
