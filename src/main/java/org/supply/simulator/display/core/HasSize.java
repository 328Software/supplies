package org.supply.simulator.display.core;

/**
 * Created by Alex on 6/19/2014.
 */
public interface HasSize {

    /**
     *
     * @return
     */
    int getRows();

    /**
     *
     * @return
     */
    int getCols();

    /**
     *
     * @param rows
     * @param cols
     */
    void setSize(int rows, int cols);
}
