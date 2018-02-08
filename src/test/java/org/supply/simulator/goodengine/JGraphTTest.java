package org.supply.simulator.goodengine;

import org.jgrapht.Graph;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.alg.flow.GusfieldEquivalentFlowTree;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.data.entity.impl.BasicPositions;
import org.supply.simulator.util.DataGenerator;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.util.PositionsUtil;

import java.util.List;
import java.util.Objects;

public class JGraphTTest {
    DataGenerator generator;

    BasicEdge e1;
    BasicEdge e2;
    BasicEdge e3;

    BasicNode v1;
    BasicNode v2;
    BasicNode v3;
    BasicEdge k1;
    BasicEdge k2;
    BasicEdge k3;

    BasicNode n1;
    BasicNode n2;
    BasicNode n3;

    @Before
    public void create() {
        generator=new DataGenerator();
    }



    @Test
    public void graphEdmondsKarpTest()  {

        Graph<BasicNode, BasicEdge> g = createGraph();

        g.vertexSet().stream().forEach(v->System.out.println(v.getName()));

        EdmondsKarpMFImpl<BasicNode,BasicEdge> alg = new EdmondsKarpMFImpl(g);

        BasicNode n1 = alg.getCurrentSource();
        if (Objects.isNull(n1)) {
            System.out.println("Source: null");
        } else {
            System.out.println("Source: "+n1.getName());
        }

        BasicNode n2 = alg.getCurrentSink();
        if (Objects.isNull(n2)) {
            System.out.println("Sink: null");
        } else {
            System.out.println("Sink: "+n2.getName());
        }

//        System.out.println("v1->v2: "+ alg.getMaximumFlow(n1,n2));

//        System.out.println("v2->v1: "+alg.getMaximumFlow(n2,n1));
//        alg.
//        alg.getFlowMap().entrySet().stream().forEach(v -> System.out.println(v.getKey().getName()+" | "+v.getValue()));
    }

    @Test
    public void graphGusfieldTest()  {

        Graph<BasicNode, BasicEdge> g = createGraph();

//        g.vertexSet().stream().forEach(v->System.out.println());
        g.vertexSet().stream().forEach(v->System.out.println(v.getName()+"  = "+g.edgesOf(v)));
        g.edgeSet().stream().forEach(e->System.out.println(e + "|"+e.getName()+"|"+g.getEdgeWeight(e)));

//        g.getEdge()

//        EdmondsKarpMFImpl<BasicNode,Edge> alg = new EdmondsKarpMFImpl(g);
        GusfieldEquivalentFlowTree<BasicNode,BasicEdge> alg = new GusfieldEquivalentFlowTree(g);

//        BasicNode n1 = alg.getFlowDirection();
//
//        if (Objects.isNull(n1)) {
//            System.out.println("dir: null");
//        } else {
//            System.out.println("dir: "+n1.getName());
//        }

        Double n = alg.calculateMaximumFlow(v1,n3);
        if (Objects.isNull(n)) {
            System.out.println("flow: null");
        } else {
            System.out.println("flow: "+n);
        }



    }

    @After
    public void end() {

    }

    private Graph<BasicNode, BasicEdge> createGraph()
    {
        //System.out.println("hey");
        Graph<BasicNode, BasicEdge> g = new SimpleWeightedGraph<>(BasicEdge.class);

//        g.

        List<BasicNode> nodes = generator.threeNodes("v");
        List<BasicNode> nodes2 = generator.threeNodes("n");

        nodes.stream().forEach(v ->  g.addVertex(v));
        nodes2.stream().forEach(v ->  g.addVertex(v));
//        nodes.stream().forEach(v ->  System.out.println(v.getName()));
        e1 = new BasicEdge();
        e1.setName("e1");
        e2 = new BasicEdge();
        e2.setName("e2");
        e3 = new BasicEdge();
        e3.setName("e3");

        v1 = nodes.get(0);
        v2 = nodes.get(1);
        v3 = nodes.get(2);

        g.addEdge(v1,v2,e1);
        g.setEdgeWeight(e1,5.0);
//        g.addEdge(v2,v3,e2);
//        g.setEdgeWeight(e2,10.0);
        g.addEdge(v1,v3,e3);
        g.setEdgeWeight(e3,2.0);

        k1 = new BasicEdge();
        k1.setName("k1");
        k2 = new BasicEdge();
        k2.setName("k2");
        k3 = new BasicEdge();
        k3.setName("k3");

        n1 = nodes2.get(0);
        n2 = nodes2.get(1);
        n3 = nodes2.get(2);

        g.addEdge(n1,n2,k1);
        g.setEdgeWeight(k1,0.5);
//        g.addEdge(v2,v3,e2);
//        g.setEdgeWeight(e2,10.0);
        g.addEdge(n1,n3,k3);
        g.setEdgeWeight(k3,1.0);

        g.addEdge(v3,n1,k2);
        g.setEdgeWeight(k2,3);

        return g;
//    return null;
    }


}
