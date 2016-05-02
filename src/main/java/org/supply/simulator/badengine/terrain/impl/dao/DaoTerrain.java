package org.supply.simulator.badengine.terrain.impl.dao;

import org.supply.simulator.badengine.Partitions;
import org.supply.simulator.badengine.terrain.Terrain;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;

/**
 * Created by Brandon on 7/21/2014.
 */
public class DaoTerrain implements Terrain {
//    TerrainDAO terrainDAO;

    @Override
    public Partitions<TerrainChunk> partition() {
        return null;
    }
}
