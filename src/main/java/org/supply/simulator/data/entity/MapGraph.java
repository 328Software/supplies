package org.supply.simulator.data.entity;

import java.util.Set;

public interface MapGraph<N extends Node,E extends Edge> {

    Set<N> getNodeSet();

    Set<E> getEdgeSet();
}
