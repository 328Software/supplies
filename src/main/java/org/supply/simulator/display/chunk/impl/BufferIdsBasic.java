package org.supply.simulator.display.chunk.impl;

import org.supply.simulator.display.chunk.BufferIds;

/**
 * Created by Alex on 6/17/2014.
 */
public class BufferIdsBasic implements BufferIds {

    private int vertexAttributesId;
    private int indicesBufferId;
    private int colorsArrayId;
    private int verticesArrayId;


    @Override
    public void setVertexAttributesId(int vertexAttributesId) {
        this.vertexAttributesId = vertexAttributesId;

    }

    @Override
    public void setIndicesBufferId(int indicesBufferId) {
        this.indicesBufferId = indicesBufferId;
    }

    @Override
    public void setColorsArrayId(int colorsArrayId) {
        this.colorsArrayId = colorsArrayId;

    }

    @Override
    public void setVerticesArrayId(int verticesArrayId) {
        this.verticesArrayId = verticesArrayId;

    }

    @Override
    public int getVertexAttributesId() {
        return this.vertexAttributesId;
    }

    @Override
    public int getIndicesBufferId() {
        return this.indicesBufferId;
    }

    @Override
    public int getColorsArrayId() {
        return this.colorsArrayId;
    }

    @Override
    public int getVerticesArrayId() {
        return this.verticesArrayId;
    }
}
