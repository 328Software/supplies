package org.supply.simulator.badengine.terrain.chunk;

import java.util.List;

/**
 * Created by Brandon on 7/20/2014.
 */
public class TerrainChunk{
    float[] positions;
    byte colors[];

    List<Quad> quads;
    Integer rowIndex, columnIndex;

    public TerrainChunk() {
//        quads = new ArrayList<Quad>();
    }

    public float[] getPositions() {
        return positions;
    }

    public void setPositions(float[] positions) {
        this.positions = positions;
    }

    public byte[] getColors() {
        return colors;
    }

    public void setColors(byte[] colors) {
        this.colors = colors;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }
}
