package org.supply.simulator.display.manager.chunk.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.renderable.chunk.impl.BasicChunkRenderable;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;

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
        visibleRenderables = new ArrayList<BasicChunkRenderable>();

    }

    @Override @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    protected Collection<BasicChunkRenderable> getRenderablesToAdd(Camera view) {
        ArrayList<BasicChunkRenderable> chunkRenderables = new ArrayList<>();
        if (isFirst) {
            isFirst=false;
            long timeStart = System.currentTimeMillis();
            logger.info("Doing");
            Collection<BasicChunk> chunks = chunkDAO.findAll();
            logger.info("Did build in " + (System.currentTimeMillis()-timeStart) + " ms");
            for(BasicChunk chunk: chunks) {
                BasicChunkRenderable chunkRenderable = new BasicChunkRenderable();
                chunkRenderable.setEntity(chunk);
                chunkRenderables.add(chunkRenderable);
            }
        }
        return chunkRenderables;
    }

    @Override
    protected Collection<BasicChunkRenderable> getRenderablesToRemove(Camera view) {
        return new ArrayList<BasicChunkRenderable>();
    }

    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }
}
