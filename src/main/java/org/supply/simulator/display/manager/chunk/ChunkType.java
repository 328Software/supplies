package org.supply.simulator.display.manager.chunk;

/**
 * Created by Alex on 7/17/2014.
 */
public interface ChunkType{

    Integer getIndicesBufferId();
    void setIndicesBufferId(Integer id);
    Integer getRows();
    Integer getColumns();
}
