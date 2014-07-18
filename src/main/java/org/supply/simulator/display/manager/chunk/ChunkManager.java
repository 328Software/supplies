package org.supply.simulator.display.manager.chunk;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.manager.Manager;

/**
 * Holds and manages all the of chunk SupplyRenderable objects
 *
 * Created by Alex on 6/17/2014.
 */
public interface ChunkManager<V extends ChunkRenderable> extends Manager<V> {


    public void setIndexEngine(ChunkIndexEngine engine);
}
