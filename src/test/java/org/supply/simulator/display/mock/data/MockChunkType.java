package org.supply.simulator.display.mock.data;

import org.supply.simulator.data.attribute.entity.ChunkType;

/**
 * Created by Alex on 9/7/2014.
 */
public class MockChunkType implements ChunkType {

    Long id;
    Integer rows, columns;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Integer getRows() {
        return rows;
    }

    @Override
    public Integer getColumns() {
        return columns;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
