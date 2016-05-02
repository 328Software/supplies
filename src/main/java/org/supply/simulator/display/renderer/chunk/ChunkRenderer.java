package org.supply.simulator.display.renderer.chunk;


import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 9/7/2014.
 */
public interface ChunkRenderer extends EntityRenderer<Chunk> {

    /**
     *
     * @param chunkIndexEngine
     */
    public void setChunkIndexEngine(ChunkIndexEngine chunkIndexEngine);



}
