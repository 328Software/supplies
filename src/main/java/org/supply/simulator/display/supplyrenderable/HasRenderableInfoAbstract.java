package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.opengl.GL11;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;

/**
 * Abstract class to implements all the getters/setters of HasRenderableInfo. It also adds some utility methods.
 *
 * Created by Alex on 6/20/2014.
 */
public abstract class HasRenderableInfoAbstract extends HasLogger implements HasRenderableInfo {

    private static ArrayList<Integer> indicesBufferIdArray;

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
    public void setIndicesBufferId(int indicesBufferId) {
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
    public int getColumns() {
        return this.columns;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public int[] getAttributeLocations () {
        return this.locations;

    }

    /**
     * Prints OpenGL error types
     *
     * @param className
     */
    public void printOpenGLError(String className) {
        switch(GL11.glGetError()) {
            case GL11.GL_NO_ERROR: //logger.info("OpenGl error in "+className+": GL_NO_ERROR");
                break;
            case GL11.GL_INVALID_ENUM: logger.error("OpenGl error in "+className+": GL_INVALID_ENUM");
                break;
            case GL11.GL_INVALID_VALUE: logger.error("OpenGl error in "+className+": GL_INVALID_VALUE");
                break;
            case GL11.GL_INVALID_OPERATION: logger.error("OpenGl error in "+className+": GL_INVALID_OPERATION");
                break;
            case GL11.GL_OUT_OF_MEMORY: logger.error("OpenGl error in "+className+": GL_OUT_OF_MEMORY");
                break;
            case GL11.GL_STACK_UNDERFLOW: logger.error("OpenGl error in "+className+": GL_STACK_UNDERFLOW");
                break;
            case GL11.GL_STACK_OVERFLOW: logger.error("OpenGl error in "+className+": GL_STACK_OVERFLOW");
                break;
        }
    }


}
