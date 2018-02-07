package org.supply.simulator.badengine.graph;

import org.jgrapht.VertexFactory;
import org.supply.simulator.data.entity.Node;

/**
 * NodeFactory needs to impl VertexFactory for jgrapht reasons
 *
 */
public interface NodeFactory extends VertexFactory<Node> { //,Builder<Node>


}
