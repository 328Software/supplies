package org.supply.simulator.util;

import org.supply.simulator.data.entity.Positions;

import java.util.Collection;

public class PositionsUtil {
    public static void copyXYZvalues(Collection<Positions> source, Collection<Positions> target) {
        if (source.size() != target.size()) {
            throw new RuntimeException("Source and target Positions sets not the same size");
        }
        Positions[] sP = (Positions[]) source.toArray();
        for (Positions tP : target) {

        }

    }

    public static void copyXYZvalues(Positions source, Positions target) {
                System.arraycopy(source.getValue(),0,target.getValue(),0,3);
    }

    public static void movePositionsX(Collection<Positions> ps, float dx) {
        ps.stream().forEach(p -> PositionsUtil.movePositionsX(p,dx));
    }

    public static void movePositionsY(Collection<Positions> ps, float dy) {
        ps.stream().forEach(p -> PositionsUtil.movePositionsY(p,dy));
    }

    public static void movePositionsXY(Collection<Positions> ps, float dx, float dy) {
        ps.stream().forEach(p -> PositionsUtil.movePositionsXY(p,dx,dy));
    }

    public static void movePositionsZ(Collection<Positions> ps, float dz) {
        ps.stream().forEach(p -> PositionsUtil.movePositionsZ(p,dz));
    }

    public static void movePositionsAll(Collection<Positions> ps, float dx, float dy, float dz) {
        ps.stream().forEach(p -> PositionsUtil.movePositionsAll(p,dx,dy,dz));
    }

    public static void movePositionsX(Positions p, float dx) {
        for (int offset = 0; offset<p.getValue().length; offset += p.getVertexSize()) {
            p.getValue()[offset] += dx;
        }
    }

    public static void movePositionsY(Positions p, float dy) {
        for (int offset = 0; offset<p.getValue().length; offset += p.getVertexSize()) {
            p.getValue()[offset+1] += dy;
        }
    }

    public static void movePositionsXY(Positions p, float dx, float dy) {
        for (int offset = 0; offset<p.getValue().length; offset += p.getVertexSize()) {
            p.getValue()[offset] += dx;
            p.getValue()[offset+1] += dy;
        }
    }

    public static void movePositionsZ(Positions p, float dz) {
        for (int offset = 0; offset<p.getValue().length; offset += p.getVertexSize()) {
            p.getValue()[offset+2] += dz;
        }
    }

    public static void movePositionsAll(Positions p, float dx, float dy, float dz) {
        for (int offset = 0; offset<p.getValue().length; offset += p.getVertexSize()) {
            p.getValue()[offset] += dx;
            p.getValue()[offset+1] += dy;
            p.getValue()[offset+2] += dz;
        }
    }
}
