package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.OpenGLDebugger;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.logging.HasLogger;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicChunkTest extends HasLogger {

    private BasicChunk chunk;
    @Before
    public void createFixture () {
        DisplayCoreTest.build("BasicChunkTest");
        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getData(100,100));
        chunk.setAttributeLocations(new int[] {0,1});
        chunk.build();

        if (chunk.isBuilt()==false) {
            logger.error("Chunk failed to build");
            System.exit(-1);
        }

        OpenGLDebugger.printChunkBuffers(chunk);

        logger.info("Chunk built successfully");
        logger.info("OPENGL BUFFERS:");
        logger.info(chunk.getEntityBufferId());
        logger.info(chunk.getPositionsArrayId());
        logger.info(chunk.getColorsArrayId());
        logger.info(chunk.getVertexAttributesId());
    }

    @Test
    public void renderChunk () {


    }

    @After
    public  void destroyChunk () {
        chunk.destroy();
        if (chunk.isDestroyed()==false) {
            logger.error("Chunk failed to destroy");
            System.exit(-1);
        }
        logger.info("Chunk destroyed successfully");
        DisplayCoreTest.destroy();
    }
}
