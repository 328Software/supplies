package org.supply.simulator.collections;

import org.supply.simulator.data.attribute.Attribute;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/14
 * Time: 11:19 AM
 *
 * A custom collection for storing attributes.
 *
 *
 */
public interface AttributeMap<K,V extends Attribute> extends Map<K,V> {

}
