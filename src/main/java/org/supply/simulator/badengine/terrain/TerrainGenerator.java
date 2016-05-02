package org.supply.simulator.badengine.terrain;

import org.supply.simulator.badengine.Generator;

/**
 * Created by Brandon on 7/20/2014.
 */
public interface TerrainGenerator extends Generator<Terrain> {
    @Override
    public Terrain generate();
}
