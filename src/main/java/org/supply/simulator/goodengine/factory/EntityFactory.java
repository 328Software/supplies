package org.supply.simulator.goodengine.factory;

import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class EntityFactory {

    public List<Node> getNodes() {
        return new ArrayList<Node>();
    }

    public List<Edge> getEdges() {
        return new ArrayList<Edge>();
    }
}
