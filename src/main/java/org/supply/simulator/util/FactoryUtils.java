package org.supply.simulator.util;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;

public class FactoryUtils {

    public static Positions newTexturedColorPositions(float topLeftX, float topLeftY, float topLeftZ, float length, float width) {
        Positions entityData = Positions.newTexturedColorPositions();
        Vertex v0 = entityData.getVertex(0);
        Vertex v1 = entityData.getVertex(1);
        Vertex v2 = entityData.getVertex(2);
        Vertex v3 = entityData.getVertex(3);
        v0.setXYZ( topLeftX,       topLeftY,        topLeftZ); v0.setRGB(1, 1, 1); v0.setST(0, 0);
        v1.setXYZ( topLeftX,       topLeftY-length, topLeftZ); v1.setRGB(1, 1, 1); v1.setST(0, 1);
        v2.setXYZ( topLeftX+width, topLeftY-length, topLeftZ); v2.setRGB(1, 1, 1); v2.setST(1, 1);
        v3.setXYZ( topLeftX+width, topLeftY,        topLeftZ); v3.setRGB(1, 1, 1); v3.setST(1, 0);

        return entityData;
    }
}
