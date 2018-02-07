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
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicMapGraph;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.util.GraphUtils;

import java.util.Set;

public class NodeGraphGenerator implements MapGraphGenerator {

    NodeFactory nodeFactory;

    @Override
    public MapGraph generate() {
        System.out.println("GENERATING");
        Graph g = generateGraph();

        // this is pretty bad, need different way to determine first node.
//        Node n = (Node)g.vertexSet().iterator().next();
//        arrangeNodes(g,n);

        System.out.println("SINL "+Math.sin(60*Math.PI/180) +  "      COS:"+Math.cos(60*Math.PI/180));
        GraphUtils.printGraph(g);
//        g.vertexSet().forEach(n->arrangeNodes(g,(Node)n));


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
    private void arrangeNodes(Graph g, Node n) {
        Set<Edge> edges = g.edgesOf(n);

        int count = 0;
        for(Edge e : edges) {
//            float scale = count/edges
            Node src = e.getSource();
            Node tgt = e.getTarget();
            if (src.equals(n)) { //only follow edges that start at the src Node
//                GraphUtils.copyXYZvalues(src,tgt);

                arrangeNodes(g,tgt);
            }
            count++;
        }
    }

    /**
     * This uses the Barabási-Albert growth and preferential attachment graph generator.
     *
     * @return
     */
    private Graph<BasicNode, BasicEdge> generateGraph() {
        int m0 = 1;     // number of initial nodes
        int e  = 1;     // number of edges of each new node added during the network growth
        int m1 = 7;     // final number of nodes


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
