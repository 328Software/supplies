package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.ChunkIndexEngine;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 7/17/2014.
 */
public class ChunkIndexEngineTest extends HasLogger {

    IndexEngine indexEngine;

    MockDisplayCore core;
    ChunkType type;

    @Before
    public void createFixture () {
        indexEngine = new ChunkIndexEngine();
        core = new MockDisplayCore();
        core.build("ChunkIndexEngineTest");
    }

    @Test
    public void chunkIndexEngineTest () {
        type= new BasicChunkType();
        type.setColumns(20);
        type.setRows(20);

        int bufferId = indexEngine.get(type).getIndexId();

        logger.info("Succesfully created chunk index opengl buffer:"+bufferId);
        
    }

    @After
    public void destroyFixture() {
        indexEngine.done(type);
        core.destroy();

    }
}