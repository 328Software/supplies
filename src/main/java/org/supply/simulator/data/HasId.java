package org.supply.simulator.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HasId<I> {
    I getId();
    void setId(I id);
}
