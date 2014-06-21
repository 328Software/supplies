package org.supply.simulator.display.core;

/**
 * Created by Alex on 6/20/2014.
 */
public abstract class HasSizeAbstract {

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return columns;
    }

    public void setCols(int columns) {
        this.columns = columns;
    }

    public void setSize(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;

    }

    protected int rows;
    protected int columns;

}
