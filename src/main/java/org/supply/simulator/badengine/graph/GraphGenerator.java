package org.supply.simulator.badengine.graph;

import org.jgrapht.Graph;
import org.supply.simulator.badengine.Generator;

public interface GraphGenerator<N,E> extends Generator<Graph<N,E>> {

    void setGenerator(GraphGenerator generator);

}
