package org.supply.simulator.display.manager.chunk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.core.DisplayCoreTest;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;

/**
 * Created by Alex on 6/28/2014.
 */
public class BasicChunkTest  {

    private BasicChunk chunk;
    @Before
    public void createFixture () {
        DisplayCoreTest.build();
        chunk = new BasicChunk();
        chunk.setData(MockChunkManager.getData(100,100));
        chunk.setAttributeLocations(new int[] {0,1});
        chunk.build();

        if (chunk.isBuilt()==false) {
            System.exit(-1);
        }



        System.out.println("Chunk built successfully");
        System.out.println("OPENGL BUFFERS:");
        System.out.println(chunk.getEntityBufferId());
        System.out.println(chunk.getPositionsArrayId());
        System.out.println(chunk.getColorsArrayId());
        System.out.println(chunk.getVertexAttributesId());
    }

    @Test
    public void renderChunk () {


    }

    @After
    public  void destroyChunk () {
        chunk.destroy();
        if (chunk.isDestroyed()==false) {
            System.exit(-1);
        }
        System.out.println("Chunk destroyed successfully");
        DisplayCoreTest.destroy();
    }
}
