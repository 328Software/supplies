package org.supply.simulator.core.container;

import org.supply.simulator.data.attribute.Attribute;
import org.supply.simulator.data.entity.Entity;

import java.util.Collection;

public interface AttributeContainer {

    Collection<Attribute> getAll();

    void add(Attribute e);

}
