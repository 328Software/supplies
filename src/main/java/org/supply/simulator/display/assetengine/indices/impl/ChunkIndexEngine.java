package org.supply.simulator.display.assetengine.indices.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.indices.AbstractIndexEngine;
import org.supply.simulator.display.assetengine.indices.IndexHandle;
import org.supply.simulator.display.assetengine.indices.IndexEngine;

import java.nio.IntBuffer;

/**
 * Created by Alex on 7/7/2014.
 */
public class ChunkIndexEngine
        extends AbstractIndexEngine<ChunkType>
        implements IndexEngine<ChunkType> {



    public ChunkIndexEngine() {
        super();
    }

    @Override
    protected IndexHandle createHandle (ChunkType key) {
        IndexHandle handle = new BasicIndexHandle();
        handle.setIndexId(createIndicesId(key.getRows(),key.getColumns()));

        return handle;
    }

    @Override
    protected void destroyHandle(ChunkType key) {
        IndexHandle handle = handleMap.remove(key);
        GL15.glDeleteBuffers(handle.getIndexId());
    }
}
