package org.supply.simulator.display.manager.chunk;

import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.logging.HasLogger;

import java.util.Collection;
import java.util.List;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicChunkDataTest extends HasLogger {
    private BasicChunkData<List<Float>,List<Byte>,List<Integer>> data;
    private int rows;
    private int columns;

    public BasicChunkDataTest () {
        rows =100;
        columns =100;
    }

    @Before
    public void createData() {
        data = MockChunkManager.getChunkData(rows, columns, 0, 0);
        logger.info("Created chunk data");
    }

    @Test
    public void checkSetters () {
        if (data.getColors()==null||data.getPositions()==null) {
            logger.error("Chunk data null");
//           System.exit(-1);
        }
        if (data.getColumns()!=columns||data.getRows()!=rows) {
            logger.error("Rows and columns incorrect");
//            System.exit(-1);
        }

        logger.info("Chunk data verified");
    }


}
