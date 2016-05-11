package org.supply.simulator.data.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface Chunk extends Entity<Long> {

//

    Colors getColors();

    void setColors(Colors colors);



    Positions getPositions();

    void setPositions(Positions positions);


    void setVertexAttributesId(int vertexAttributesId);

    void setIndicesBufferId(int indicesBufferId);

    void setColorsArrayId(int colorsArrayId);

    void setPositionsArrayId(int positionsArrayId);

    int getVertexAttributesId();

    int getIndicesBufferId();

    int getColorsArrayId();

    int getPositionsArrayId();

}
