package org.supply.simulator.data.entity.impl;

import org.jgrapht.Graph;
import org.supply.simulator.data.entity.MapGraph;

import java.util.Set;

public class BasicMapGraph implements MapGraph<BasicNode,BasicEdge> {
    Graph graph;

    @Override
    public Set<BasicNode> getNodeSet() {
        return this.graph.vertexSet();
    }

    @Override
    public Set<BasicEdge> getEdgeSet() {
        return this.graph.edgeSet();
    }

    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph g) {
        this.graph = g;
    }
}
