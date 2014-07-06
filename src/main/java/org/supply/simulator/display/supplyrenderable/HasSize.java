package org.supply.simulator.display.supplyrenderable;

/**
 * Created by Alex on 6/19/2014.
 */
public interface HasSize {

    /**
     *  Returns rows.
     *
     * @return
     */
    int getRows();

    /**
     *  Returns columns;
     *
     * @return
     */
    int getColumns();

    /**
     * Sets rows;
     *
     */
    void setRows(int rows);

    /**
     * Sets columns
     *
     */
    void setColumns(int columns);
}
