package org.supply.simulator.display.manager;

import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;
import org.supply.simulator.display.window.Camera;

import java.util.Collection;

/**
 * Created by Alex on 6/29/2014.
 */
public interface Manager<K,V extends SupplyRenderable> extends Collection<V>, Iterable<V> {

    /**
     *
     * @param view
     */
    public void update(Camera view);

}
