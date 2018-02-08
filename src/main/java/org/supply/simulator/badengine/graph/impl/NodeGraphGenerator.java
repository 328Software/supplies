package org.supply.simulator.badengine.graph.impl;

import org.jgrapht.Graph;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.lwjgl.Sys;
import org.supply.simulator.badengine.graph.MapGraphGenerator;
import org.supply.simulator.badengine.graph.NodeFactory;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.MapGraph;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicMapGraph;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.util.EntityUtils;
import org.supply.simulator.util.GraphUtils;
import org.supply.simulator.util.PositionsUtil;

import java.util.LinkedHashSet;
import java.util.Set;

public class NodeGraphGenerator implements MapGraphGenerator {

    NodeFactory nodeFactory;

    @Override
    public MapGraph generate() {
//        System.out.println("GENERATING");
        Graph<Node,Edge> g = generateGraph();

        // this is pretty bad, need different way to determine first node.
//        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
//        GraphUtils.printGraph(g);
//        g.vertexSet().forEach(EntityUtils::printNode);
        Node n = g.vertexSet().iterator().next();
//        System.out.println("|||||||||||||||||||||"+n.getName()+"|||||||||||||||||||||||||||||");
//        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        arrangeNodes(g,n,0);

//        g.vertexSet().forEach(EntityUtils::printNode);
//        GraphUtils.printGraph(g);
//


        BasicMapGraph mapGraph = new BasicMapGraph();
        mapGraph.setGraph(g);
        return mapGraph;
    }

    /**
     * Recursively changes positions of nodes to arrange them well
     *
     * @param g
     * @param n
     */
    protected void arrangeNodes(Graph g, Node n, int c1) {
        Set<Edge> edges = g.edgesOf(n);

        float angle = 180/(edges.size());
        int c2 = 0;
        for(Edge e : edges) {
//            float scale = count/edges
            Node src = e.getSource();
            Node tgt = e.getTarget();



            System.out.println(src.getName()+"  C2:"+c1+" C2:"+c2);
            if (src.equals(n)) { //only follow edges that start at the src Node


//                GraphUtils.copyXYZvalues(src,tgt);
                float dx = .3f*c1;
                float dy = .3f*c2;
//                float dx = Math.round(1000*Math.sin(angle*count*Math.PI/180))/1000;
//                float dy = Math.round(1000*Math.cos(angle*count*Math.PI/180))/1000;
                System.out.println(tgt.getName()+"          dx:"+dx+"    dy:"+dy);
                PositionsUtil.movePositionsXY(tgt.getPositions(), dx, dy);

                arrangeNodes(g,tgt,c1+1);
                c2++;
            }






            e.setPositions(generateEdgePositions(src,tgt));
        }
    }

    private Set<Positions> generateEdgePositions(Node source, Node target) {
        BasicPositions p = BasicPositions.newTexturedColorPositions();
        float[] data = new float[BasicPositions.NUMBER_OF_VERTICES * p.getVertexSize()];
        int offset = 0;

        float[] sourceCentroid = PositionsUtil.calculateCentroidXYZ(p);
        // vertex 0
        data[offset + 0] = 0; //x
        data[offset + 1] = 0; //y
        data[offset + 2] = 0; //z

        offset *= p.getVertexSize();

        // vertex 1
        data[offset + 0] = 0; //x
        data[offset + 1] = 0; //y
        data[offset + 2] = 0; //z

        offset *= p.getVertexSize();

        // vertex 2
        data[offset + 0] = 0; //x
        data[offset + 1] = 0; //y
        data[offset + 2] = 0; //z

        offset *= p.getVertexSize();

        // vertex 3
        data[offset + 0] = 0; //x
        data[offset + 1] = 0; //y
        data[offset + 2] = 0; //z

        Set<Positions> set = new LinkedHashSet<>();

        return set;
    }
    /**
     * This uses the Barabási-Albert growth and preferential attachment graph generator.
     *
     * @return
     */
    protected Graph<Node, Edge> generateGraph() {
        int m0 = 1;     // number of initial nodes
        int e  = 1;     // number of edges of each new node added during the network growth
        int m1 = 5;     // final number of nodes


        OurBarabasiAlbertGraphGenerator gen = new OurBarabasiAlbertGraphGenerator(m0,e,m1);
        Graph g = new SimpleWeightedGraph<BasicNode,BasicEdge>(BasicEdge.class);


        gen.generateGraph(g,nodeFactory,null);

        return g;
    }

    @Override
    public void setNodeFactory(NodeFactory nodeFactory) {
        this.nodeFactory=nodeFactory;
    }
}
