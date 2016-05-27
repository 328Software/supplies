package org.supply.simulator.display.assetengine.indices;

/**
 * Created by Alex on 9/11/2014.
 */
public class IndexHandle {
    private Integer indexId;


    private int rows;
    private int columns;

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId=indexId;

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
