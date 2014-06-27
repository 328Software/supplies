package org.supply.simulator.display.renderableinfo;

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
    int getColumns();

    /**
     *
     * @param rows
     * @param cols
     */
    void setSize(int rows, int cols);
}
