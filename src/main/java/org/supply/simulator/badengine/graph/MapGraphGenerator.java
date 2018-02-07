package org.supply.simulator.badengine.graph;

import org.supply.simulator.badengine.Generator;
import org.supply.simulator.data.entity.MapGraph;

public interface MapGraphGenerator extends Generator<MapGraph> {

    void setNodeFactory(NodeFactory nodeFactory);

}
