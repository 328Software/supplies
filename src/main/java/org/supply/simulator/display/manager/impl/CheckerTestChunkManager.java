package org.supply.simulator.display.manager.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;
import org.supply.simulator.badengine.terrain.impl.SimpleTerrainGenerator;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.data.attribute.entity.impl.BasicChunkType;
import org.supply.simulator.data.entity.impl.BasicChunk;
import org.supply.simulator.data.statistic.entity.impl.BasicChunkColors;
import org.supply.simulator.data.statistic.entity.impl.BasicChunkPositions;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.display.window.CameraImpl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Brandon on 7/8/2014.
 */
public class CheckerTestChunkManager extends AbstractManager<BasicChunkRenderer> implements Manager<BasicChunkRenderer> {
    private int chunkRows = 8;//chunkType.rows();
    private int chunkColumns =8;// chunkType.columns();
    private int totalChunkRows = 25;
    private int totalChunkColumns = 25;

    private ChunkDAO chunkDAO;
    private SessionFactory sessionFactory;
    private boolean isFirst;

    public CheckerTestChunkManager () {
        super();
        isFirst = true;
        visibleRenderables = new ArrayList<BasicChunk>();

    }
//
//    @Override
//    protected Collection<BasicChunkRenderable> getRenderablesToAdd(Camera camera) {
//        return null;
//    }

    @Override
    protected Collection<BasicChunk> getRenderablesToRemove(CameraImpl camera) {
        return new ArrayList<BasicChunk>();
    }

    @Override /*@Transactional(value = "chunk",propagation = Propagation.REQUIRES_NEW)*/
    protected java.util.Collection<BasicChunk> getRenderablesToAdd(CameraImpl view) {
        Collection<BasicChunk> newChunks = new ArrayList<BasicChunk>();
        if (isFirst) {
            isFirst=false;
            int count = 0;
            BasicChunkType type = new BasicChunkType();
            type.setColumns(chunkColumns);
            type.setRows(chunkRows);
            SimpleTerrainGenerator generator = new SimpleTerrainGenerator();

            for(TerrainChunk terrainChunk: generator.generate().partition()) {

                logger.info("creating chunk " + (count++));
                BasicChunk chunk = new BasicChunk();
                BasicChunkPositions positions = new BasicChunkPositions();
                BasicChunkColors colors = new BasicChunkColors();
                positions.setValue(terrainChunk.getPositions());
                colors.setValue(terrainChunk.getColors());
                chunk.setType(type);
                chunk.setChunkColors(colors);
                chunk.setChunkPositions(positions);

                newChunks.add(chunk);
                storeChunk(chunk);
            }
        }
        return newChunks;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void storeChunk(BasicChunk chunk) {
        sessionFactory.getCurrentSession().saveOrUpdate(chunk);
    }

    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}