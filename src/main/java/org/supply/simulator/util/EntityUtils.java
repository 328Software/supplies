package org.supply.simulator.util;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Node;

public class EntityUtils {

    public static void printNode(Node n) {
        System.out.println("=========NODE:"+n.getName()+"===========");
        PositionsUtil.printFirstPositions(n.getPositions());
        System.out.println("====================================");
    }

    public static void printEntity(Entity n) {
        System.out.println("=========ENTITY POS===========");
        PositionsUtil.printFirstPositions(n.getPositions());
        System.out.println("====================================");
    }
}
