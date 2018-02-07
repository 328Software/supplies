package org.supply.simulator.util;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicPositions;

public class PositionsUtil {


    public static void movePositionsX(BasicPositions p, float dx) {
        float[] value = p.getValue();
        int size = p.getVertexSize();
        for (int offset=0; offset<value.length; offset=offset+size) {
            value[offset] = value[offset] + dx;
        }
    }

    public static void movePositionsY(BasicPositions p, float dy) {
        float[] value = p.getValue();
        int size = p.getVertexSize();
        for (int offset=0; offset<value.length; offset=offset+size) {
            value[offset+1] = value[offset+1] + dy;
        }

    }

    public static void movePositionsZ(BasicPositions p, float dz) {
        float[] value = p.getValue();
        int size = p.getVertexSize();
        for (int offset=0; offset<value.length; offset=offset+size) {
            value[offset+2] = value[offset+2] + dz;
        }

    }


}
