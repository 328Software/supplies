package org.supply.simulator.display.supplyrenderable;

/**
 * Interface to hold all the handles to OpenGl buffer objects
 *
 * Created by Alex on 6/17/2014.
 */
public interface HasBufferIds {

    /**
     * Sets the vertex attributes buffer object id.
     *
     * @param vertexAttributesId
     */
    void setVertexAttributesId(int vertexAttributesId);

    /**
     * Sets the indices buffer object id.
     *
     */
    void setIndicesBufferId(int indicesBufferId);

    /**
     * Sets the colors array buffer object id.
     *
     * @param colorsArrayId
     */
    void setColorsArrayId(int colorsArrayId);

    /**
     * Sets the positions array buffer object id.
     *
     * @param verticesArrayId
     */
    void setPositionsArrayId(int verticesArrayId);

    /**
     * Sets the entity buffer object id.
     *
     * @param entityBufferID
     */
    void setEntityBufferId(int entityBufferID);

    /**
     * Returns the vertex attributes buffer object id.
     *
     * @return
     */
    int getVertexAttributesId();

    /**
     * Returns the indices buffer object id.
     *
     * @return
     */
    int getIndicesBufferId();

    /**
     * Returns the positions array buffer object id.
     *
     * @return
     */
    int getPositionsArrayId();

    /**
     * Returns the colors array buffer object id.
     *
     * @return
     */
    int getColorsArrayId();

    /**
     * Returns the entity buffer object id.
     *
     * @return
     */
    int getEntityBufferId();
}
