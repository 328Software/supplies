package org.supply.simulator.data.attribute.entity.impl;

import org.supply.simulator.data.attribute.entity.AbstractEntityType;
import org.supply.simulator.data.attribute.entity.ChunkType;

/**
 * Created by Alex on 9/7/2014.
 */
public class BasicChunkType extends AbstractEntityType implements ChunkType {

    Integer rows, columns;

    @Override
    public Integer getRows() {
        return rows;
    }

    @Override
    public Integer getColumns() {
        return columns;
    }

    @Override
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public void setColumns(Integer columns) {
        this.columns = columns;
    }

}
