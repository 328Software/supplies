package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkType;
import org.supply.simulator.display.simple.SimpleDisplayCore;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicChunkIndexEngineTest extends HasLogger {

    ChunkIndexEngine indexEngine;

    SimpleDisplayCore core;

    @Before
    public void createFixture () {
        indexEngine = new BasicChunkIndexEngine();
        core = new SimpleDisplayCore();
        core.build("BasicChunkIndexEngineTest");
    }

    @Test
    public void chunkIndexEngineTest () {
        BasicChunkType type = new BasicChunkType();
        type.setColumns(20);
        type.setRows(20);

        int bufferId = indexEngine.get(type);

        logger.info("Succesfully created chunk index opengl buffer:"+bufferId);
        
    }

    @After
    public void destroyFixture() {
        core.destroy();

    }
}
