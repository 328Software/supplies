package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.data.HasId;
import org.supply.simulator.display.manager.chunk.ChunkData;


/**
 *
 * Created by Alex on 6/17/2014.
 */
public class BasicChunkData<V,C,I>
        implements ChunkData<V,C,I>, HasId<Long> {

    private long id;

    private int rows;
    private int columns;

    private Integer rowIndex;
    private Integer columnIndex;

    // The amount of bytes an element has
    public static final int POSITION_ELEMENT = 4 ;
    public static final int COLOR_ELEMENT = 1;

    // Elements per parameter
    public static final int POSITION_COUNT = 4;
    public static final int COLOR_COUNT = 4;
    public static final int TEXTURE_COUNT = 2;

    // Bytes per parameter
    public static final int POSITION_BYTES = POSITION_COUNT * POSITION_ELEMENT;
    public static final int COLOR_BYTES = COLOR_COUNT * COLOR_ELEMENT;
    public static final int TEXTURE_BYTE = TEXTURE_COUNT * POSITION_ELEMENT;

    // Byte offsets per parameter
    public static final int POSITION_BYTE_OFFSET = 0;
    public static final int COLOR_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_COUNT;
    public static final int TEXTURE_BYTE_OFFSET = COLOR_BYTE_OFFSET + COLOR_COUNT;

    private V positions;
    private C colors;
    private I indices;


    @Override
    public V getPositions() {
        return this.positions;
    }

    @Override
    public C getColors() {
        return this.colors;
    }

    @Override
    public I getIndices() {
        return this.indices;
    }

    @Override
    public void setPositions(V buf) {
        positions = buf;
    }

    @Override
    public void setColors(C buf) {
        colors = buf;
    }

    @Override
    public void setIndices(I buf) {
        indices = buf;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public Integer getRowIndex() {
        return rowIndex;
    }

    @Override
    public void setRowIndex(Integer indexRows) {
        this.rowIndex = indexRows;
    }

    @Override
    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public void setColumnIndex(Integer indexColumns) {
        this.columnIndex = indexColumns;
    }
}
