package org.supply.simulator.display.chunk;

/**
 * Created by Alex on 6/17/2014.
 */
public interface BufferIds {

    /**
     *
     * @param vertexAttributesId
     */
    void setVertexAttributesId(int vertexAttributesId);

    /**
     *
     * @param indicesBufferId
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
    void setVerticesArrayId(int verticesArrayId);

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
    int getVerticesArrayId();
}
