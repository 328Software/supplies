package org.supply.simulator.util;

import org.jgrapht.Graph;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicPositions;

import java.util.LinkedHashSet;
import java.util.Set;

public class GraphUtils {

    public static Set<Positions> generateEdgePositions(Node source, Node target) {
        float EDGE_WIDTH = .4f;

        Positions p = FactoryUtils.newTexturedColorPositions();

        PositionsUtil.printPositions(p);

        float[] sourceCentroid = PositionsUtil.calculateCentroidXYZ(source.getPositions());
        float[] targetCentroid = PositionsUtil.calculateCentroidXYZ(target.getPositions());
        System.out.println("SOURCE "+source.getName()+" X:" +sourceCentroid[0]+" Y:"+sourceCentroid[1]+" Z:"+sourceCentroid[2]);
        System.out.println("TARGET "+target.getName()+" X:" +targetCentroid[0]+" Y:"+targetCentroid[1]+" Z:"+targetCentroid[2]);

        int offset = 0;
        // vertex 0

        p.getValue()[offset + 0] = sourceCentroid[0]+EDGE_WIDTH/2f; //x
        p.getValue()[offset + 1] = sourceCentroid[1]; //y
        p.getValue()[offset + 2] = sourceCentroid[2]; //z

        offset += p.getVertexSize();

        // vertex 1
        p.getValue()[offset + 0] = sourceCentroid[0]-EDGE_WIDTH/2f; //x
        p.getValue()[offset + 1] = sourceCentroid[1]; //y
        p.getValue()[offset + 2] = sourceCentroid[2]; //z

        offset += p.getVertexSize();
        // vertex 2
        p.getValue()[offset + 0] = targetCentroid[0]+EDGE_WIDTH/2f; //x
        p.getValue()[offset + 1] = targetCentroid[1]; //y
        p.getValue()[offset + 2] = targetCentroid[2]; //z

        offset += p.getVertexSize();
        // vertex 3
        p.getValue()[offset + 0] = targetCentroid[0]-EDGE_WIDTH/2f; //x
        p.getValue()[offset + 1] = targetCentroid[1]; //y
        p.getValue()[offset + 2] = targetCentroid[2]; //z


        Set<Positions> set = new LinkedHashSet<>();
        set.add(p);
        return set;
    }

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
