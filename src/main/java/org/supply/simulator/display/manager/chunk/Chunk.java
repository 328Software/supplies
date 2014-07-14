package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.supplyrenderable.ChunkSupplyRenderable;

import java.util.List;

/**
 * A SupplyRenderable object to represent a chunk of the ground.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Chunk extends ChunkSupplyRenderable/*, Entity<
        EntityAttribute<Long>,
        EntityStatistic<Object,Long>,
        Long
        >, HasId<Long>*/ {

    /**
     *
     * @param indexManager
     */
    public void setIndexManager(ChunkIndexManager indexManager);

    /**
     * Sets the chunk data. Will be pushed to OpenGl Buffers on build().
     *
     * @param data chunk data object
     */
    public void setData(ChunkData<List<Float>, List<Byte>> data);

    /**
     * Gets the chunk data.
     *
     * @return chunk data object
     */
    public ChunkData<List<Float>, List<Byte>> getData();




}
