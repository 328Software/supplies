package org.supply.simulator.data.attribute.entity;

/**
 * Created by Alex on 9/14/2014.
 */
public interface ChunkType extends EntityType<Long> {
    public Integer getRows();

    public Integer getColumns();

    public void setRows(Integer rows);

    public void setColumns(Integer columns);
}
