package org.supply.simulator.display.renderableinfo;

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
     * @param rows
     * @param cols
     */
    void setIndicesBufferId(int rows, int cols);

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
