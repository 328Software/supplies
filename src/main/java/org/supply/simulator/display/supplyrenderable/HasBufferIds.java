package org.supply.simulator.display.supplyrenderable;

/**
 * Created by Alex on 6/17/2014.
 */
public interface HasBufferIds {

    /**
     *
     * @param vertexAttributesId
     */
    void setVertexAttributesId(int vertexAttributesId);

    /**
     *
     */
    void setIndicesBufferId(int indicesBufferId);

    /**
     *
     * @param colorsArrayId
     */
    void setColorsArrayId(int colorsArrayId);

    /**
     *
     * @param verticesArrayId
     */
    void setPositionsArrayId(int verticesArrayId);

    /**
     *
     * @param entityBufferID
     */
    void setEntityBufferId(int entityBufferID);

    /**
     *
     * @return
     */
    int getVertexAttributesId();

    /**
     *
     * @return
     */
    int getIndicesBufferId();

    /**
     *
     * @return
     */
    int getColorsArrayId();

    /**
     *
     * @return
     */
    int getPositionsArrayId();

    /**
     *
     * @return
     */
    int getEntityBufferId();
}
