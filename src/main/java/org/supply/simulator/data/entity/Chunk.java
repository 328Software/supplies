package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Chunk extends Entity {

//

    Colors getColors();
    void setColors(Colors colors);

    Positions getPositions();
    void setPositions(Positions positions);

    int getVertexAttributesId();
    void setVertexAttributesId(int vertexAttributesId);

    int getIndicesBufferId();
    void setIndicesBufferId(int indicesBufferId);

    int getColorsArrayId();
    void setColorsArrayId(int colorsArrayId);

    int getPositionsArrayId();
    void setPositionsArrayId(int positionsArrayId);

}
