package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.assetengine.indices.ChunkIndexHandle;

/**
 * Object that contains all the raw chunk data from the database
 *
 * Created by Alex on 6/17/2014.
 */
public interface ChunkData<V, C> {

    /**
     * Returns positions buffer data.
     *
     * @return positions buffer data
     */
    V getPositions();

    /**
     * Returns colors buffer data.
     *
     * @return colors buffer data
     */
    C getColors();

    /**
     * Sets positions buffer data.
     *
     * @param buf
     */
    void setPositions(V buf);

    /**
     * Sets colors buffer data.
     *
     * @param buf colors buffer data
     */
    void setColors(C buf);

    /**
     * Sets the columnIndex of the coordinates of the chunk
     *
     * @param columnIndex the top left corner column
     */
    public void setColumnIndex(Integer columnIndex);

    /**
     * Sets the rowIndex of the coordinates of the chunk
     *
     * @param rowIndex the top left corner row
     */
    public void setRowIndex(Integer rowIndex);

    /**
     * gets the columnIndex of the coordinates of the chunk
     *
     * @return the top left corner column
     */
    public Integer getColumnIndex();

    /**
     * gets the rowIndex of the coordinates of the chunk
     *
     * @return the top left corner row
     */
    public Integer getRowIndex();



}
