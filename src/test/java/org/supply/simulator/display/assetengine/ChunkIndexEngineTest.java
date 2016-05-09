package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.ChunkIndexEngine;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.logging.HasLogger;

import static org.supply.simulator.data.attribute.entity.EntityType.CHUNK;

/**
 * Created by Alex on 7/17/2014.
 */
public class ChunkIndexEngineTest extends HasLogger {

    IndexEngine indexEngine;

    MockDisplayCore core;

    @Before
    public void createFixture () {
        indexEngine = new ChunkIndexEngine(20, 20);
        core = new MockDisplayCore();
        core.build("ChunkIndexEngineTest");
    }

    @Test
    public void chunkIndexEngineTest () {

        int bufferId = indexEngine.get(CHUNK).getIndexId();

        logger.info("Succesfully created chunk index opengl buffer:"+bufferId);
        
    }

    @After
    public void destroyFixture() {
        indexEngine.done(CHUNK);
        core.destroy();

    }
}
