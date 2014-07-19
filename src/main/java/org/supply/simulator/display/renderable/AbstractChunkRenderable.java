package org.supply.simulator.display.renderable;

import org.supply.simulator.display.manager.chunk.ChunkRenderable;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.renderable.AbstractSupplyRenderable;

/**
 * Abstract class to implements all the getters/setters of HasRenderableInfo. It also adds some utility methods.
 *
 * Created by Alex on 6/20/2014.
 */
public abstract class AbstractChunkRenderable extends AbstractSupplyRenderable implements ChunkRenderable {

    //private static ArrayList<Integer> indicesBufferIdArray;

//    protected int rows;
//    protected int columns;

    protected int vertexAttributesId;

    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;
    protected ChunkType chunkType;

    public void setChunkType(ChunkType chunkType) {
        this.chunkType = chunkType;
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


    public ChunkType getChunkType() {
        return chunkType;
    }







}
