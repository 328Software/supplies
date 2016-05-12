package org.supply.simulator.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/2/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HasId<I extends Comparable<I>> extends Comparable<HasId<I>> {
    I getId();

    @Override
    default int compareTo(HasId<I> o) {
        return getId().compareTo(o.getId());
    }
}
