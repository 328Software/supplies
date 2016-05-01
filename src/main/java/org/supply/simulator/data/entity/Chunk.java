package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.statistic.entity.ChunkColors;
import org.supply.simulator.data.statistic.entity.ChunkPositions;
import org.supply.simulator.data.statistic.entity.EntityStatistic;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Chunk extends Entity<EntityAttribute,EntityStatistic,Long> {
    public ChunkType getChunkType();

    public void setChunkType(ChunkType chunkType);



    public ChunkColors getChunkColors();

    public void setChunkColors(ChunkColors chunkColors);



    public ChunkPositions getChunkPositions();

    public void setChunkPositions(ChunkPositions chunkPositions);

}
