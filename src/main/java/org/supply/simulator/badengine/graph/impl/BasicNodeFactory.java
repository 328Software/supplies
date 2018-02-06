package org.supply.simulator.badengine.graph.impl;

import org.supply.simulator.badengine.graph.NodeFactory;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.Vertex;
import org.supply.simulator.data.entity.impl.BasicNode;
import org.supply.simulator.util.FactoryUtils;

import java.util.HashSet;
import java.util.Set;

public class BasicNodeFactory implements NodeFactory {

    private static final float TOP_LEFT_X = -0.5f;
    private static final float TOP_LEFT_Y = -0.5f;
    private static final float TOP_LEFT_Z = 0f;
    private static final float LENGTH = .2f;
    private static final float WIDTH = .2f;


    private int node_count;

    public BasicNodeFactory () {
        node_count = 0;
    }

    @Override
    public Node createVertex() {
        BasicNode node = new BasicNode();
        node.setPositions(getNewPositions());
        node.setName("n"+node_count);
        node_count=node_count+1;
        return node;
    }

    private Set<Positions> getNewPositions () {
        Set<Positions> ps = new HashSet<>();


        Positions p = FactoryUtils.newTexturedColorPositions(TOP_LEFT_X,TOP_LEFT_Y, TOP_LEFT_Z, LENGTH, WIDTH);

        return ps;
    }




}
