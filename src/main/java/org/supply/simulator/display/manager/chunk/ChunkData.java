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
     * Returns indices buffer data.
     *
     * @return indices buffer data
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
     * @param buf colors buffer data
     */
    void setColors(C buf);

    /**
     * Sets indices buffer data.
     *
     * @param buf indices buffer data.
     */
    void setIndices(I buf);

    /**
     *  Returns rows.
     *
     * @return rows
     */
    int getRows();

    /**
     *  Returns columns;
     *
     * @return columns
     */
    int getColumns();

    /**
     * Sets rows
     *
     * @param rows rows int
     */
    void setRows(int rows);

    /**
     * Sets columns
     *
     * @param columns columns int
     */
    void setColumns(int columns);


    /**
     *
     * @return
     */
    int getIndexRows();

    /**
     *
     * @param indexRows
     */
    void setIndexRows(int indexRows);

    /**
     *
     * @return
     */
    int getIndexColumns();

    /**
     *
     * @param indexColumns
     */
    void setIndexColumns(int indexColumns);
}
