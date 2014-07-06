package org.supply.simulator.display.supplyrenderable;

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
     */
    void setRows(int rows);

    /**
     *
     */
    void setColumns(int columns);

    /**
     *
     * @param rows
     * @param cols
     */
    void setSize(int rows, int cols);
}
