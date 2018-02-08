package org.supply.simulator.util;

import org.junit.Test;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.display.factory.TextMenuSubElementBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

public class PositionsUtilTest {
    @Test
    public void centroidTest() {

        Positions p = FactoryUtils.newUntexturedColorPositions(1f,1f,0,1,1);
        float[] xyz = PositionsUtil.calculateCentroidXYZ(p);

        PositionsUtil.printPositions(p);
//        System.out.println("X:" +xyz[0]+" Y:"+xyz[1]+" Z:"+xyz[2]);
        assert xyz[0]==1.5f;
        assert xyz[1]==0.5f;
        assert xyz[2]==0.0f;

        Positions p2 = FactoryUtils.newUntexturedColorPositions(2f,1f,0,1,1);
        Positions p3 = FactoryUtils.newUntexturedColorPositions(3f,2f,0,1,1);
        PositionsUtil.printPositions(p2);
        PositionsUtil.printPositions(p3);
        Set<Positions> s = new LinkedHashSet<>();
        s.add(p);
        s.add(p2);
        s.add(p3);


        xyz = PositionsUtil.calculateCentroidXYZ(s);
        System.out.println("X:" +xyz[0]+" Y:"+xyz[1]+" Z:"+xyz[2]);
    }

    @Test
    public void positionsUtilCopyTest() {

        BasicPositions positions = BasicPositions.newTexturedColorPositions();
        float[] data = new float[positions.getVertexSize() * BasicPositions.NUMBER_OF_VERTICES];
        int offset =positions.getVertexSize();
        data[0] = 2;
        data[1] = 2;
        data[2] = 2;
        data[offset] = 2;
        data[offset + 1] = 2;
        data[offset + 2] = 2;
        System.out.println("==1==========================");
        System.out.println(data.length);
        for (float d : data) {
            System.out.print(d + " |");
        }
        System.out.println();
        positions.setValue(data);

        BasicPositions positions2 = BasicPositions.newTexturedColorPositions();
        data = new float[positions2.getVertexSize() * BasicPositions.NUMBER_OF_VERTICES];
        offset =positions2.getVertexSize();
        data[0] = 1;
        data[1] = 1;
        data[2] = 1;
        data[offset] = 1;
        data[offset + 1] = 1;
        data[offset + 2] = 1;
        for (float d : data) {
            System.out.print(d + " |");
        }
        System.out.println();
        positions2.setValue(data);
        System.out.println("==2==========================");
        PositionsUtil.copyXYZvalues(positions,positions2);
        System.out.println(positions.getValue().length);
        for (float d : positions.getValue()) {
            System.out.print(d + " |");
        }
        System.out.println();
        for (float d : positions2.getValue()) {
            System.out.print(d + " |");
        }
    }
}
