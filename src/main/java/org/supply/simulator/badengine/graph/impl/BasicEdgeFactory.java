package org.supply.simulator.badengine.graph.impl;

import org.supply.simulator.Factory;
import org.supply.simulator.data.entity.impl.BasicEdge;
import org.supply.simulator.data.entity.impl.BasicNode;

public class BasicEdgeFactory implements Factory<BasicEdge> {

    private BasicNode target;
    private BasicNode source;


    @Override
    public BasicEdge build() {
        BasicEdge edge = new BasicEdge();
        edge.setTarget(target);
        edge.setSource(source);
        return edge;
    }


    public void setTarget(BasicNode target) {
        this.target = target;
    }

    public void setSource(BasicNode source) {
        this.source = source;
    }

}
