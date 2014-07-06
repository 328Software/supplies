package org.supply.simulator.display.manager.chunk;

import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicChunkDataTest {
    private BasicChunkData<FloatBuffer, ByteBuffer, IntBuffer> data;
    private int rows;
    private int columns;

    public BasicChunkDataTest () {
        rows =100;
        columns =100;
    }

    @Before
    public void createData() {

        data = MockChunkManager.getData(rows,columns);
        System.out.println("created chunk data");
    }

    @Test
    public void checkSetters () {
        if (data.getColorBuffer()==null||data.getPositionsBuffer()==null||data.getIndicesBuffer()==null) {
           System.exit(-1);
        }
        if (data.getColumns()!=columns||data.getRows()!=rows) {
            System.exit(-1);
        }

        System.out.println("chunk data verified");
    }


}
