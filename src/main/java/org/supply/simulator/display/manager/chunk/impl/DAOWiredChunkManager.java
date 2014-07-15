package org.supply.simulator.display.manager.chunk.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 7/13/2014.
 */
public class DAOWiredChunkManager  extends AbstractChunkManager<BasicChunk> {

    private int chunkRows = 128;
    private int chunkColumns = 128;
    private int totalChunkRows = 10;
    private int totalChunkColumns = 10;

    private ChunkDAO chunkDAO;


    private boolean isFirst;

    public DAOWiredChunkManager () {
        super();
        isFirst = true;
        chunkCollection = new ArrayList<BasicChunk>();
        indexManager = new BasicChunkIndexManager();

    }

    @Override @Transactional(propagation = Propagation.REQUIRED)
    protected void updateChunks(Camera view) {
        if (isFirst) {
            isFirst=false;

            for(Chunk chunk: chunkDAO.findAll()) {
                chunk.setAttributeLocations(new int[]{0,1});
                chunkCollection.add((BasicChunk)chunk);
            }
        }
    }



    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

}
