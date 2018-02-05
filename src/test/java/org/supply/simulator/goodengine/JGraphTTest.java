package org.supply.simulator.goodengine;

import org.jgrapht.Graph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.flow.GusfieldGomoryHuCutTree;
import org.jgrapht.alg.flow.MaximumFlowAlgorithmBase;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.display.extra.DataGenerator;

import java.util.List;

public class JGraphTTest {
    DataGenerator generator;

    @Before
    public void create() {
        generator=new DataGenerator();
    }

    @Test
    public void runJGraphTTest()  {

//        Graph<Node, Edge> g = createGraph();
//        MaximumFlowAlgorithmBase<Node,Edge> alg = new EdmondsKarpMFImpl(g);
//
//        Node n1 = alg.getCurrentSource();
//        System.out.println("Source: "+n1.getName());
//
//
//        Node n2 = alg.getCurrentSink();
//        System.out.println("Sink: "+n2.getName());
//
//        System.out.println("v1->v2: "+ alg.getMaximumFlow(n1,n2));
//
//        System.out.println("v2->v1: "+alg.getMaximumFlow(n2,n1));

    }

    @After
    public void end() {

    }

    private Graph<Node, Edge> createGraph()
    {
//        Graph<Node, Edge> g = new SimpleWeightedGraph<>(BasicEdge.class);
//
//        List<BasicNode> nodes = generator.threeNodes();
//
//        nodes.stream().forEach(v ->  g.addVertex(v));
//
//        BasicEdge e1 = new BasicEdge();
//        BasicEdge e2 = new BasicEdge();
//        BasicEdge e3 = new BasicEdge();
//
//        BasicNode v1 = nodes.get(0);
//        BasicNode v2 = nodes.get(1);
//        BasicNode v3 = nodes.get(2);
//
//        g.addEdge(v1,v2,e1);
//        g.setEdgeWeight(e1,5.0);
//        g.addEdge(v2,v3,e2);
//        g.setEdgeWeight(e2,10.0);
//        g.addEdge(v1,v3,e3);
//        g.setEdgeWeight(e3,2.0);


//        return g;
    return null;
    }


}
