package org.supply.simulator.data.entity;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.statistic.entity.Colors;
import org.supply.simulator.data.statistic.entity.Positions;
import org.supply.simulator.data.statistic.entity.EntityStatistic;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Chunk extends Entity<EntityAttribute,EntityStatistic,Long> {

//    @Override
    //TODO REMOVE THIS AND HIBERNATE BREAKS
    public ChunkType getType();
//
//    public void setType(ChunkType chunkType);
//
//

    public Colors getColors();

    public void setColors(Colors colors);



    public Positions getPositions();

    public void setPositions(Positions positions);


    public void setVertexAttributesId(int vertexAttributesId);

    public void setIndicesBufferId(int indicesBufferId);

    public void setColorsArrayId(int colorsArrayId);

    public void setPositionsArrayId(int positionsArrayId);

    public int getVertexAttributesId();

    public int getIndicesBufferId();

    public int getColorsArrayId();

    public int getPositionsArrayId();

}
