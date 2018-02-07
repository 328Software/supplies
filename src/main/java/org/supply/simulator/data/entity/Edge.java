package org.supply.simulator.data.entity;

public interface Edge extends Entity {

    Node getSource();

    Node getTarget();

    void setSource(Node fromNode);

    void setTarget(Node toNode);
}
