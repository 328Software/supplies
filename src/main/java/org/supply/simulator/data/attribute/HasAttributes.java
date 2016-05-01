package org.supply.simulator.data.attribute;


/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/15/14
 * Time: 4:39 PM
 *
 * A property that every thing (entities and interactions) in existence has.
 * This interface acts as a wrapper of AttributeMap. Attributes can be added
 * and then a collection can get returned.
 */
public interface HasAttributes<A extends Attribute> extends Iterable<A> {
    /**
     *
     * @param attribute
     */
    void addAttribute(A attribute);

    void removeAttribute(Object attributeId);

    A getAttribute(Object attributeId);

    boolean hasAttribute(Object attributeId);
}
