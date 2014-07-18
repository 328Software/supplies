package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.buildable.SupplyBuildable;

/**
 * A SupplyRenderable object to represent a chunk of the ground.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Chunk<D extends ChunkData> extends SupplyBuildable/*, Entity<
        EntityAttribute<Long>,
        EntityStatistic<Object,Long>,
        Long
        >, HasId<Long>*/ {

//    /**
//     *
//     * @param indexEngine
//     */
//    public void setChunkIndexEngine(ChunkIndexEngine indexEngine);

    /**
     * Sets the chunk data. Will be pushed to OpenGl Buffers on build().
     *
     * @param data chunk data object
     */
    public void setData(D data);

    /**
     * Gets the chunk data.
     *
     * @return chunk data object
     */
    public D getData();




}
