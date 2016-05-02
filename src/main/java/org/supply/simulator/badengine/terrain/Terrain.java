package org.supply.simulator.badengine.terrain;

import org.supply.simulator.badengine.Partitionable;
import org.supply.simulator.badengine.Partitions;

/**
 * Created by Brandon on 7/20/2014.
 */
public interface Terrain extends Partitionable<org.supply.simulator.badengine.terrain.chunk.TerrainChunk> {




    @Override
    public Partitions<org.supply.simulator.badengine.terrain.chunk.TerrainChunk> partition();
}
