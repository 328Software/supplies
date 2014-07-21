package org.supply.simulator.display.manager.chunk.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.supply.simulator.badengine.terrain.TerrainChunk;
import org.supply.simulator.badengine.terrain.impl.SimpleTerrainGenerator;
import org.supply.simulator.core.dao.chunk.ChunkDAO;
import org.supply.simulator.display.manager.chunk.*;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 7/8/2014.
 */
public class CheckerTestChunkManager extends AbstractChunkManager<BasicChunkRenderable> implements ChunkManager<BasicChunkRenderable> {
    private int chunkRows = 20;//chunkType.rows();
    private int chunkColumns =20;// chunkType.columns();
    private int totalChunkRows = 25;
    private int totalChunkColumns = 25;

    private ChunkDAO chunkDAO;
    private SessionFactory sessionFactory;
    private boolean isFirst;

    public CheckerTestChunkManager () {
        super();
        isFirst = true;
        visibleChunks = new ArrayList<BasicChunkRenderable>();

    }



    @Override /*@Transactional(value = "chunk",propagation = Propagation.REQUIRES_NEW)*/
    protected java.util.Collection<BasicChunkRenderable> getChunksToAdd(Camera view) {
        Collection<BasicChunkRenderable> newChunks = new ArrayList<BasicChunkRenderable>();
        if (isFirst) {
            isFirst=false;
            int count = 0;
            BasicChunkType type = new BasicChunkType();
            type.setColumns(chunkColumns);
            type.setRows(chunkRows);
            SimpleTerrainGenerator generator = new SimpleTerrainGenerator();

            for(TerrainChunk terrainChunk: generator.generate().partition()) {

//            for (int i = 0; i<totalChunkRows*chunkRows;i=i+chunkRows) {
//                for (int j = 0; j<totalChunkColumns*chunkColumns;j=j+chunkColumns) {
                logger.info("creating chunk " + (count++));
                BasicChunk chunk = new BasicChunk();
                BasicChunkData<float[],byte[]> data = new BasicChunkData<float[],byte[]>();
                data.setColors(terrainChunk.getColors());
                data.setPositions(terrainChunk.getPositions());
                chunk.setData(data);
                chunk.setChunkType(type);
                chunk.setAttributeLocations(new int[]{0, 1, 2});
//                    chunk.setData(getChunkData(chunkRows,chunkColumns,i,j));
                BasicChunkRenderable renderable = chunk.build();

//                    sessionFactory.getCurrentSession().flush();
                newChunks.add(renderable);
                storeChunk(chunk);
//                }
//            }
            }
        }
        return newChunks;
    }

    @Override
    protected Collection<BasicChunkRenderable> getChunksToRemove(Camera view) {
        return new ArrayList<BasicChunkRenderable>();
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
