package org.supply.simulator.util;

import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicPositions;

public class PositionsUtil {


    public static void movePositionsX(BasicPositions p, float dx) {
        for (int offset=0; offset<p.getValue().length; offset=offset+p.getVertexSize()) {
            p.getValue()[offset] = p.getValue()[offset] + dx;
        }
    }

    public static void movePositionsY(BasicPositions p, float dy) {
        for (int offset=0; offset<p.getValue().length; offset=offset+p.getVertexSize()) {
            p.getValue()[offset+1] = p.getValue()[offset+1] + dy;
        }
    }

    public static void movePositionsZ(BasicPositions p, float dz) {
        for (int offset=0; offset<p.getValue().length; offset=offset+p.getVertexSize()) {
            p.getValue()[offset+2] = p.getValue()[offset+2] + dz;
        }
    }
}
