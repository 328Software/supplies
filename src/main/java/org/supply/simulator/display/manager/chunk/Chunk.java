package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * A SupplyRenderable object to represent a chunk of the ground.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Chunk extends SupplyRenderable {

    public void setIndexManager(ChunkIndexManager indexManager);


}
