package org.supply.simulator.display.renderable.chunk;

import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.display.renderable.EntityRenderable;

/**
 * Interface for all objects that will be rendered by the Display Engine. All OpenGl code will be in classes that 
 * implement this interface(with the exception of the ShaderEngine).
 *
 * Created by Alex on 6/29/2014.
 */
public interface ChunkRenderable extends EntityRenderable {

    public ChunkType getChunkType();

    public float[] getChunkPositions();

    public byte[] getChunkColors();

    public void setVertexAttributesId(int vertexAttributesId);

    public void setIndicesBufferId(int indicesBufferId);

    public void setColorsArrayId(int colorsArrayId);

    public void setPositionsArrayId(int positionsArrayId);

    public int getVertexAttributesId();

    public int getIndicesBufferId();

    public int getColorsArrayId();

    public int getPositionsArrayId();





}
