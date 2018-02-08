package org.supply.simulator.util;

import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;

public class FactoryUtils {

    public static Positions newUntexturedColorPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        BasicPositions positions = BasicPositions.newTexturedColorPositions();
        Vertex v0 = positions.getVertex(0);
        Vertex v1 = positions.getVertex(1);
        Vertex v2 = positions.getVertex(2);
        Vertex v3 = positions.getVertex(3);
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 1, 1); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(1, 1, 1); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(1, 1, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        return positions;
    }

    public static Positions newTexturedColorPositions(String textureKey, float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        BasicPositions positions = BasicPositions.newTexturedColorPositions();
        Vertex v0 = positions.getVertex(0);
        Vertex v1 = positions.getVertex(1);
        Vertex v2 = positions.getVertex(2);
        Vertex v3 = positions.getVertex(3);
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 1, 1); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(1, 1, 1); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(1, 1, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        positions.setTextureKey(textureKey);

        return positions;
    }

    public static Positions newTexturedColorPositions() {
        BasicPositions positions = BasicPositions.newTexturedColorPositions();
        Vertex v0 = positions.getVertex(0);
        Vertex v1 = positions.getVertex(1);
        Vertex v2 = positions.getVertex(2);
        Vertex v3 = positions.getVertex(3);
        v0.setXYZ( 0, 0, 0); v0.setRGB(1, 1, 1); v0.setST(0, 0);
        v1.setXYZ( 0, 0, 0); v1.setRGB(1, 1, 1); v1.setST(0, 1);
        v2.setXYZ( 0, 0, 0); v2.setRGB(1, 1, 1); v2.setST(1, 1);
        v3.setXYZ( 0, 0, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        return positions;
    }
}
