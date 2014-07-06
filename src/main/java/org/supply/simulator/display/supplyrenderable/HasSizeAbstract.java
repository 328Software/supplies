package org.supply.simulator.display.supplyrenderable;

/**
 * Abstract class to implements all the getters/setters of HasSize
 *
 * Created by Alex on 6/20/2014.
 */
public abstract class HasSizeAbstract implements HasSize {

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

    protected int rows;
    protected int columns;

}
