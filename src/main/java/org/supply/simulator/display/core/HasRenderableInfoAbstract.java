package org.supply.simulator.display.core;

/**
 * Created by Alex on 6/20/2014.
 */
public abstract class HasRenderableInfoAbstract implements HasRenderableInfo {

    protected int rows;
    protected int columns;

    protected int vertexAttributesId;
    protected int indicesBufferId;
    protected int colorsArrayId;
    protected int positionsArrayId;
    protected int entityBufferId;

    protected int[] locations;


    @Override
    public void setEntityBufferId(int entityBufferId) {
        this.entityBufferId = entityBufferId;
    }

    @Override
    public void setAttributeLocations(int[] locations) {
        this.locations = locations;
    }

    @Override
    public void setVertexAttributesId(int vertexAttributesId) {
        this.vertexAttributesId = vertexAttributesId;

    }

    @Override
    public void setIndicesBufferId(int rows, int cols) {
        //this.indicesBufferId = indicesBufferId;
    }

    @Override
    public void setColorsArrayId(int colorsArrayId) {
        this.colorsArrayId = colorsArrayId;

    }

    @Override
    public void setPositionsArrayId(int verticesArrayId) {
        this.positionsArrayId = verticesArrayId;

    }

    @Override
    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;

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
    public int getPositionsArrayId() {
        return this.positionsArrayId;
    }

    @Override
    public int getEntityBufferId() {
        return entityBufferId;
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getCols() {
        return this.columns;
    }

    @Override
    public int[] getAttributeLocations () {
        return this.locations;

    }

}
