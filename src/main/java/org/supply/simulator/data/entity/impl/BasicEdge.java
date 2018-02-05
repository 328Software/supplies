package org.supply.simulator.data.entity.impl;

import org.supply.simulator.data.entity.AbstractUnit;
import org.supply.simulator.data.entity.Edge;
import org.supply.simulator.data.entity.Node;
import org.supply.simulator.data.entity.Positions;

import java.util.Set;

/**
 * BasicEdge, used to represent supply lines
 */
public class BasicEdge extends AbstractUnit implements Edge {


    private Node fromNode;
    private Node toNode;

    private Set<Positions> p;

    public Set<Positions> getPositions() {
        return p;
    }

    public void setPositions(Set<Positions> p) {
        this.p = p;
    }

    private String name;

    protected Object	getSource() {
        return fromNode;
    }

    protected Object	getTarget() {
        return toNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private float magnitude;


}
