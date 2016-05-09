package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Colors;
import org.supply.simulator.data.entity.Positions;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunk implements Chunk {
    private Long id;
    private Colors colors;
    private Positions positions;
    private ChunkType chunkType;

    protected int vertexAttributesId;

    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;

    @Override
    public void setType(EntityType type) {
        this.chunkType = (ChunkType)type;
    }


    //TODO remove this method hibernate breaks
    public void setType(ChunkType type) {
        this.chunkType = type;
    }

    //TODO remove this method hibernate breaks
    public ChunkType getType() {
        return this.chunkType;
    }


    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }



    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    public void setVertexAttributesId(int vertexAttributesId) {
        this.vertexAttributesId = vertexAttributesId;
    }

    public void setIndicesBufferId(int indicesBufferId) {
        this.indicesBufferId = indicesBufferId;
    }

    public void setColorsArrayId(int colorsArrayId) {
        this.colorsArrayId = colorsArrayId;

    }

    public int getVertexAttributesId() {
        return vertexAttributesId;
    }

    public int getIndicesBufferId() {
        return indicesBufferId;
    }

    public int getColorsArrayId() {
        return colorsArrayId;
    }

    public int getPositionsArrayId() {
        return positionsArrayId;
    }


    public void setPositionsArrayId(int positionsArrayId) {
        this.positionsArrayId = positionsArrayId;

    }

//    @Override

    @Override
    public Long getId() {
        return id;
    }

   /* public Long getId() {

        return id;
    }*/

    public void setId(Long id) {
        this.id = id;
    }
}
