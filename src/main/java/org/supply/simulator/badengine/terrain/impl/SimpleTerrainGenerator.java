package org.supply.simulator.badengine.terrain.impl;

import org.supply.simulator.badengine.terrain.Terrain;
import org.supply.simulator.badengine.terrain.TerrainGenerator;
import org.supply.simulator.logging.HasLogger;

import java.io.File;

/**
 * Created by Brandon on 7/20/2014.
 */
public class SimpleTerrainGenerator extends HasLogger implements TerrainGenerator {

    Terrain terrain;

    public SimpleTerrainGenerator() {
        try {
            this.terrain = new CheckeredTestTerrain(new File("brandonLand.bgf"));
        } catch (Exception e) {
            logger.info("we failed",e);
            this.terrain = null;
        }
    }

    @Override
    public Terrain generate() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
