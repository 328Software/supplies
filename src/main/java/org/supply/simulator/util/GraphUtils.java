package org.supply.simulator.util;

import org.jgrapht.Graph;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;

public class GraphUtils {

    public static void printGraph(Graph<Node,Edge> g) {

        System.out.println("============================");
        g.vertexSet().forEach(n-> {
                    System.out.println("          NODE:"+n.getName().toUpperCase()+"      ");
                    g.edgesOf(n).forEach(e -> {
                                Node src = e.getSource();
                                Node tgt = e.getTarget();
                                System.out.println("EDGES: SRC: " + src.getName() + "          TARGET: " + tgt.getName());

                                float[] sP = src.getPositions().iterator().next().getValue();
                                float[] tP = tgt.getPositions().iterator().next().getValue();


                                System.out.println("EDGES: SRC: X" + sP[0] + " Y"+ sP[1] +" Z"+ sP[2]
                                        +"         TARGET: X" + tP[0] + " Y"+ tP[1] +" Z"+ tP[2] );
                            });
                    System.out.println("============================");
                    });





    }

    public static void copyXYZvalues(Node source, Node target) {
       PositionsUtil.copyXYZvalues(source.getPositions(),target.getPositions());
    }
}
