package org.supply.simulator.display.manager.chunk.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.display.assetengine.indices.ChunkType;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexHandle;
import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 7/13/2014.
 */
public class DAOWiredChunkManager  extends AbstractChunkManager<BasicChunk> {

    private final ChunkType chunkType = ChunkType.MEDIUM_T;

    private int chunkRows = chunkType.rows();
    private int chunkColumns = chunkType.columns();
    private int totalChunkRows = 10;
    private int totalChunkColumns = 10;

    private ChunkDAO chunkDAO;


    protected BasicChunkIndexEngine<ChunkType,BasicChunkIndexHandle> indexManager;

    private boolean isFirst;

    public DAOWiredChunkManager () {
        super();
        isFirst = true;
        chunkCollection = new ArrayList<BasicChunk>();
        indexManager = new BasicChunkIndexEngine();
        indexManager.set(chunkType,null);

    }

    @Override @Transactional(propagation = Propagation.REQUIRED)
    protected void updateChunks(Camera view) {
        if (isFirst) {
            isFirst=false;
            long timeStart = System.currentTimeMillis();
            logger.info("Doing");
            Collection<Chunk> chunks = chunkDAO.findAll();
            logger.info("Did build in " + (System.currentTimeMillis()-timeStart) + " ms");
            for(Chunk chunk: chunks) {
                chunk.setAttributeLocations(new int[]{0,1});
                chunk.setChunkIndexEngine(indexManager);
                chunkCollection.add((BasicChunk)chunk);
            }
        }
    }



    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

}
