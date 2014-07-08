package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;

/**
 * Created by Alex on 6/29/2014.
 */

public class BasicChunkManager<V extends Chunk> extends AbstractChunkManager<V> implements ChunkManager<V> {
    private ChunkDAO chunkDAO;

    public BasicChunkManager() {
         super();
    }

    @Override
    public void update(Camera view) {
        updateChunks(null);
    }

    @Override
    protected void updateChunks(Camera view) {
        BasicChunk chunk = (BasicChunk)chunkDAO.findOneByRowIndexColumnIndexRowsColumns(1,1,10,10);
        logger.info(chunk);
        logger.info(chunk.getData());
        logger.info(chunk.getData().getColors());
    }


    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }
}
