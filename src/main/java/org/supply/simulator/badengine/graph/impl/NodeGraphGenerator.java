package org.supply.simulator.badengine.graph.impl;

import org.jgrapht.Graph;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.supply.simulator.badengine.graph.MapGraphGenerator;
import org.supply.simulator.badengine.graph.NodeFactory;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.MapGraph;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicMapGraph;
import org.supply.simulator.data.entity.impl.BasicNode;

public class NodeGraphGenerator implements MapGraphGenerator {

    NodeFactory nodeFactory;

    @Override
    public MapGraph generate() {
        BasicMapGraph g = new BasicMapGraph();

        g.setGraph(generateGraph());

//        g.getNodeSet().stream().forEach(n->{
//            n.setPositions();
//        });

        return g;
    }



//    @Override
//    public Graph<BasicNode, BasicEdge> generate() {
//
//        return generateGraph(generator);
//    }

    /**
     * This uses the Barabási-Albert growth and preferential attachment graph generator.
     *
     * @return
     */
    private Graph<BasicNode, BasicEdge> generateGraph() {
        int M0 = 2;     // number of initial nodes
        int E  = 1;     // number of edges of each new node added during the network growth
        int M1 = 7;      // final number of nodes


        BarabasiAlbertGraphGenerator gen = new BarabasiAlbertGraphGenerator<Node, Edge>(M0,E,M1);
        Graph g = new SimpleWeightedGraph<BasicNode,BasicEdge>(BasicEdge.class);


        gen.generateGraph(g,nodeFactory,null);

        return g;
    }

    @Override
    public void setNodeFactory(NodeFactory nodeFactory) {
        this.nodeFactory=nodeFactory;
    }
}
