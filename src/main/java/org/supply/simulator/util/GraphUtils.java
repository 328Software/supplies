package org.supply.simulator.util;

import org.jgrapht.Graph;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.impl.BasicPositions;

import java.util.Set;

public class GraphUtils {

    public static void printGraph(Graph<Node,Edge> g) {

        System.out.println("============================");
        g.vertexSet().forEach(n-> {
                    System.out.println("          NODE:"+n.getName().toUpperCase()+"      ");
                    g.edgesOf(n).forEach(e -> {
                                Node src = e.getSource();
                                Node tgt = e.getTarget();
                                System.out.println("EDGES: SRC: " + src.getName() + "          TARGET: " + tgt.getName());
                            });
                    System.out.println("============================");
                    });





    }

//    public static void copyXYZvalues(Node source, Node target) {
//        source.getPositions().forEach(p->PositionsUtil.copyXYZvalues(p,target.getPositions())
//
//    }
}
