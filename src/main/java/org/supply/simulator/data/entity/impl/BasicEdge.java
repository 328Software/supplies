package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.AbstractUnit;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;

/**
 * BasicEdge, used to represent supply lines
 */
public class BasicEdge extends AbstractUnit implements Edge {


    private Node fromNode;
    private Node toNode;

    protected Object	getSource() {
        return fromNode;
    }

    protected Object	getTarget() {
        return toNode;
    }


    private float magnitude;


}
