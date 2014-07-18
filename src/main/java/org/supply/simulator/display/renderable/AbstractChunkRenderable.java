package org.supply.simulator.display.renderable;

import org.supply.simulator.display.manager.chunk.ChunkType;

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


    public void setPositionsArrayId(int positionsArrayId) {
        this.positionsArrayId = positionsArrayId;

    }


    public ChunkType getChunkType() {
        return chunkType;
    }





//    @Override
//        this.rows = rows;
//    }
//
//    @Override
//    public void setColumns(int columns) {
//        this.columns = columns;
//    }

//    @Override
//    public int getRows() {
//        return this.rows;
//    }
//
//    @Override
//    public int getColumns() {
//        return this.columns;
//    }






}
