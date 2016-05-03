package org.supply.simulator.display.manager.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.CameraImpl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Brandon on 7/13/2014.
 */
public class DAOWiredChunkManager  extends AbstractManager<BasicChunkRenderer> {

    private int chunkRows = 50;//chunkType.rows();
    private int chunkColumns = 50;//chunkType.columns();
    private int totalChunkRows = 10;
    private int totalChunkColumns = 10;

    private ChunkDAO chunkDAO;

    private boolean isFirst;

    public DAOWiredChunkManager () {
        super();
        isFirst = true;
        visibleRenderables = new ArrayList<BasicChunk>();

    }

    @Override @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    protected Collection<BasicChunk> getRenderablesToAdd(CameraImpl view) {
        ArrayList<BasicChunk> chunkRenderables = new ArrayList<>();
        if (isFirst) {
            isFirst=false;
            long timeStart = System.currentTimeMillis();
            logger.info("Doing");
            Collection<BasicChunk> chunks = chunkDAO.findAll();
            logger.info("Did build in " + (System.currentTimeMillis()-timeStart) + " ms");
            for(BasicChunk chunk: chunks) {
                chunkRenderables.add(chunk);
            }
        }
        return chunkRenderables;
    }

    @Override
    protected Collection<BasicChunk> getRenderablesToRemove(CameraImpl view) {
        return new ArrayList<BasicChunk>();
    }

    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }
}
