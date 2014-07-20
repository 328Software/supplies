package org.supply.simulator.display.manager.chunk.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkRenderable;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Brandon on 7/13/2014.
 */
public class DAOWiredChunkManager  extends AbstractChunkManager<BasicChunkRenderable> {

    private int chunkRows = 50;//chunkType.rows();
    private int chunkColumns = 50;//chunkType.columns();
    private int totalChunkRows = 10;
    private int totalChunkColumns = 10;

    private ChunkDAO chunkDAO;

    private boolean isFirst;

    public DAOWiredChunkManager () {
        super();
        isFirst = true;
        visibleChunks = new ArrayList<BasicChunkRenderable>();

    }

    @Override @Transactional(propagation = Propagation.REQUIRED)
    protected Collection<BasicChunkRenderable> getChunksToAdd(Camera view) {
        ArrayList<BasicChunkRenderable> chunkRenderables = new ArrayList<>();
        if (isFirst) {
            isFirst=false;
            long timeStart = System.currentTimeMillis();
            logger.info("Doing");
            Collection<Chunk> chunks = chunkDAO.findAll();
            logger.info("Did build in " + (System.currentTimeMillis()-timeStart) + " ms");
            for(Chunk chunk: chunks) {
                chunk.setAttributeLocations(new int[]{0,1,2});
                ChunkRenderable chunkRenderable = chunk.build();
                chunkRenderables.add((BasicChunkRenderable)chunkRenderable);
            }
        }
        return chunkRenderables;
    }

    @Override
    protected Collection<BasicChunkRenderable> getChunksToRemove(Camera view) {
        return new ArrayList<BasicChunkRenderable>();
    }


    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

}
