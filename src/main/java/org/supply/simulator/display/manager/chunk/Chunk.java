package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * A SupplyRenderable object to represent a chunk of the ground.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Chunk extends SupplyRenderable {

    /**
     * Sets the columnIndex of the coordinates of the chunk
     *
     * @param columnIndex the top left corner column
     */
    public void setColumnIndex(int columnIndex);

    /**
     * Sets the rowIndex of the coordinates of the chunk
     *
     * @param rowIndex the top left corner row
     */
    public void setRowIndex(int rowIndex);

    /**
     * gets the columnIndex of the coordinates of the chunk
     *
     * @return the top left corner column
     */
    public int getColumnIndex();

    /**
     * gets the rowIndex of the coordinates of the chunk
     *
     * @return the top left corner row
     */
    public int getRowIndex();

}
