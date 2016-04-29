package org.supply.simulator.display.renderable.chunk.impl;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.data.entity.Chunk;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.renderable.AbstractEntityRenderable;
import org.supply.simulator.display.renderable.chunk.ChunkRenderable;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicChunkRenderable extends AbstractEntityRenderable implements ChunkRenderable {
    public static final int INDICES_PER_VERTEX = 6;

    public BasicChunkRenderable() {
        super();
    }
    protected int vertexAttributesId;

    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;

    private Chunk chunk;


    @Override
    public void setEntity(Entity entity) {
        this.chunk = (Chunk)entity;
    }

    @Override
    public ChunkType getChunkType() {
        return chunk.getChunkType();
    }

    @Override
    public float[] getChunkPositions() {
        return chunk.getChunkPositions().getValue();
    }

    @Override
    public byte[] getChunkColors() {
        return chunk.getChunkColors().getValue();
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

}
