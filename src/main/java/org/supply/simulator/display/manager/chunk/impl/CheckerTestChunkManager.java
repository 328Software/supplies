package org.supply.simulator.display.manager.chunk.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.badengine.terrain.chunk.TerrainChunk;
import org.supply.simulator.badengine.terrain.impl.CheckeredTestTerrain;
import org.supply.simulator.badengine.terrain.impl.SimpleTerrainGenerator;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.data.attribute.entity.impl.ChunkType;
import org.supply.simulator.data.entity.impl.Chunk;
import org.supply.simulator.data.statistic.entity.impl.ChunkColors;
import org.supply.simulator.data.statistic.entity.impl.ChunkPositions;
import org.supply.simulator.display.manager.AbstractManager;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderable.chunk.impl.BasicChunkRenderable;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 7/8/2014.
 */
public class CheckerTestChunkManager extends AbstractManager<BasicChunkRenderer> implements Manager<BasicChunkRenderer> {
    private int chunkRows = 32;//chunkType.rows();
    private int chunkColumns =32;// chunkType.columns();
    private int totalChunkRows = 25;
    private int totalChunkColumns = 25;

    private ChunkDAO chunkDAO;
    private SessionFactory sessionFactory;
    private boolean isFirst;

    public CheckerTestChunkManager () {
        super();
        isFirst = true;
        visibleRenderables = new ArrayList<BasicChunkRenderable>();

    }
//
//    @Override
//    protected Collection<BasicChunkRenderable> getRenderablesToAdd(Camera camera) {
//        return null;
//    }

    @Override
    protected Collection<BasicChunkRenderable> getRenderablesToRemove(Camera camera) {
        return new ArrayList<BasicChunkRenderable>();
    }

    @Override /*@Transactional(value = "chunk",propagation = Propagation.REQUIRES_NEW)*/
    protected java.util.Collection<BasicChunkRenderable> getRenderablesToAdd(Camera view) {
        Collection<BasicChunkRenderable> newChunks = new ArrayList<BasicChunkRenderable>();
        if (isFirst) {
            isFirst=false;
            int count = 0;
            ChunkType type = new ChunkType();
            type.setColumns(chunkColumns);
            type.setRows(chunkRows);
            SimpleTerrainGenerator generator = new SimpleTerrainGenerator();

            for(TerrainChunk terrainChunk: generator.generate().partition()) {

                logger.info("creating chunk " + (count++));
                Chunk chunk = new Chunk();
                ChunkPositions positions = new ChunkPositions();
                ChunkColors colors = new ChunkColors();
                positions.setValue(terrainChunk.getPositions());
                colors.setValue(terrainChunk.getColors());
                chunk.setChunkType(type);
                chunk.setChunkColors(colors);
                chunk.setChunkPositions(positions);

                BasicChunkRenderable renderable = new BasicChunkRenderable();
                renderable.setEntity(chunk);
                newChunks.add(renderable);
                storeChunk(chunk);
//                }
//            }
            }
        }
        return newChunks;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void storeChunk(Chunk chunk) {
        sessionFactory.getCurrentSession().saveOrUpdate(chunk);
    }

    public void setChunkDAO(ChunkDAO chunkDAO) {
        this.chunkDAO = chunkDAO;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
