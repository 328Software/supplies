package org.supply.simulator.display.assetengine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.mock.MockDisplayCore;
import org.supply.simulator.logging.HasLogger;
import org.supply.simulator.util.MapUtils;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicIndexEngineTest extends HasLogger {

    BasicIndexEngine indexEngine;

    MockDisplayCore core;

    @Before
    public void createFixture () {
        indexEngine = new BasicIndexEngine();
        core = new MockDisplayCore();
        core.build("BasicIndexEngineTest");
    }

    @Test
    public void chunkIndexEngineTest () {

        int bufferId = indexEngine.get(MapUtils.newEntry(20,20)).getIndexId();

        logger.info("Succesfully created chunk index opengl buffer:"+bufferId);
        
    }

    @After
    public void destroyFixture() {
//        indexEngine.done(MapUtils.newEntry(20,20));
        core.destroy();

    }
}
