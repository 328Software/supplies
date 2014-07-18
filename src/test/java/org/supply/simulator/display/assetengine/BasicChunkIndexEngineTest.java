package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkType;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicChunkIndexEngineTest extends HasLogger {

    ChunkIndexEngine indexEngine;

    @Before
    public void createFixture () {
        indexEngine = new BasicChunkIndexEngine();
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

    }
}
