package org.supply.simulator.badengine.graph;

import org.jgrapht.Graph;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.supply.simulator.badengine.graph.impl.BasicNodeFactory;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicNode;

public class NodeGraphGenerator implements GraphGenerator<Node,Edge> {

    GraphGenerator generator;

    @Override
    public void setGenerator(GraphGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Graph<Node, Edge> generate() {

        return generateGraph(generator);
    }

    /**
     * This uses the Barabási-Albert growth and preferential attachment graph generator.
     *
     * @param generator
     * @return
     */
    private Graph<Node, Edge> generateGraph(GraphGenerator generator) {
        int M0 = 2;     // number of initial nodes
        int E  = 1;     // number of edges of each new node added during the network growth
        int M1 = 7;      // final number of nodes


        BarabasiAlbertGraphGenerator gen = new BarabasiAlbertGraphGenerator<Node, Edge>(M0,E,M1);
        Graph g = new SimpleWeightedGraph<BasicNode,BasicEdge>(BasicEdge.class);

        NodeFactory nodeFactory = new BasicNodeFactory();

        gen.generateGraph(g,nodeFactory,null);

        return g;
    }


}
