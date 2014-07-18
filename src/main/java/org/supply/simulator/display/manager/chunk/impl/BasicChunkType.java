package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.data.HasId;
import org.supply.simulator.display.manager.chunk.ChunkType;

/**
 * Created by Alex on 7/17/2014.
 */
public class BasicChunkType implements ChunkType, HasId<Long> {
    Long id;
    Integer rows, columns, indicesBufferId;


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
    public Integer getIndicesBufferId() {
        return indicesBufferId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    @Override
    public void setIndicesBufferId(Integer indicesBufferId) {
        this.indicesBufferId = indicesBufferId;
    }
}
