package org.supply.simulator.display.assetengine.indices.impl;

import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.attribute.entity.EntityType;
import org.supply.simulator.display.assetengine.indices.AbstractIndexEngine;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.IndexHandle;

/**
 * Created by Alex on 7/7/2014.
 */
public class ChunkIndexEngine
        extends AbstractIndexEngine<EntityType>
        implements IndexEngine<EntityType> {

    int rows, columns;


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ChunkIndexEngine(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    protected IndexHandle createHandle (EntityType key) {
        IndexHandle handle = new BasicIndexHandle();
        handle.setIndexId(createIndicesId(rows, columns));

        return handle;
    }

    @Override
    protected void destroyHandle(EntityType key) {
        IndexHandle handle = handleMap.remove(key);
        GL15.glDeleteBuffers(handle.getIndexId());
    }
}
