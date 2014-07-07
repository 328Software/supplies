package org.supply.simulator.display.manager.chunk;

/**
 * Object that contains all the raw chunk data from the database
 *
 * Created by Alex on 6/17/2014.
 */
public interface ChunkData<V, C, I> {

    /**
     * Returns positions buffer data.
     *
     * @return
     */
    V getPositions();

    /**
     * Returns colors buffer data.
     *
     * @return
     */
    C getColors();

    /**
     * Returns indices buffer data.
     *
     * @return
     */
    I getIndices();

    /**
     * Sets positions buffer data.
     *
     * @param buf
     */
    void setPositions(V buf);

    /**
     * Sets colors buffer data.
     *
     * @param buf
     */
    void setColors(C buf);

    /**
     * Sets indices buffer data.
     *
     * @param buf
     */
    void setIndices(I buf);

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
