package org.supply.simulator.util;


import org.lwjgl.Sys;
import org.supply.simulator.data.entity.Positions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class PositionsUtil {

    public static float[] calculateCentroidXYZ(Set<Positions> p) {
        float size = p.size();

        float[] r = p.stream().map(x->calculateCentroidXYZ(x))
                .reduce(new float[]{0,0,0},(x, v)->
                        new float[]{x[0]+v[0],(x[1]+v[1]),(x[2]+v[2])});
        r[0]=r[0]/size;
        r[1]=r[1]/size;
        r[2]=r[2]/size;

        return r;
    }
    public static float[] calculateCentroidXYZ(Positions p) {
        float[] xyz = new float[3];

        float centroidX = 0;
        float centroidY = 0;
        float centroidZ = 0;
        for (int offset=0;offset<p.getValue().length;offset+=p.getVertexSize()) {
            centroidX+=p.getValue()[offset+0];
//            System.out.println(offset+" = "+p.getValue()[offset+0]);
            centroidY+=p.getValue()[offset+1];
            centroidZ+=p.getValue()[offset+2];
        }
//        System.out.println("CX"+centroidX);
//        System.out.println("CY"+centroidX);
//        System.out.println("CZ"+centroidX);

        float num_vertices=p.getValue().length/p.getVertexSize();
//        System.out.println("#"+num_vertices);

        xyz[0]=centroidX/num_vertices;
        xyz[1]=centroidY/num_vertices;
        xyz[2]=centroidZ/num_vertices;

        return xyz;
    }

    public static void printFirstPositions(Collection<Positions> p) {
        p.forEach(PositionsUtil::printFirstPositions);
    }

    public static void printFirstPositions(Positions p) {
        float[] value = p.getValue();
        System.out.println("-----------------------------------------");
        for (int i =0; i<value.length;i+=p.getVertexSize()) {
            System.out.println("X" + value[i+0] + " Y"+ value[i+1] +" Z"+ value[i+2]);
        }
    }

    public static void printPositions(Positions p) {
        float[] value = p.getValue();
        System.out.println("============================");
        System.out.println(value.length);
        for (int i =0; i<value.length;i++) {
            if (i%10==0) System.out.print("<|> ");
            System.out.print(value[i] + " ");
        }
        System.out.print("<|>\r\n");
    }

    public static void copyXYZvalues(Collection<Positions> source, Collection<Positions> target) {
        if (source.size() != target.size()) {
            throw new RuntimeException("Source and target Positions sets not the same size");
        }

        Iterator<Positions> it = source.iterator();
        target.forEach(t->PositionsUtil.copyXYZvalues(it.next(),t));
    }

    public static void copyXYZvalues(Positions source, Positions target) {
        for (int offset = 0; offset<target.getValue().length; offset += target.getVertexSize()) {
            System.arraycopy(source.getValue(),offset,target.getValue(),offset,3);
        }
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
    public static float[] addXYZ(float[] xyz1, float[] xyz2) {
        float[] res = new float[3];
        res[0] = xyz1[0] + xyz2[0];
        res[1] = xyz1[1] + xyz2[1];
        res[2] = xyz1[2] + xyz2[2];
//        return res;
        return new float[]{xyz1[0] + xyz2[0], xyz1[1] + xyz2[1], xyz1[2] + xyz2[2]};
    }
}
