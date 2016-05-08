package org.supply.simulator.display.assetengine.indices.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.indices.AbstractIndexEngine;
import org.supply.simulator.display.assetengine.indices.IndexEngine;
import org.supply.simulator.display.assetengine.indices.IndexHandle;

import java.nio.IntBuffer;

/**
 * Created by Alex on 5/6/2016.
 */
public class UnitIndexEngine extends AbstractIndexEngine<Integer>
        implements IndexEngine<Integer> {

    private static final int INDICES_PER_VERTEX = 6;

    public UnitIndexEngine() {
        super();
    }

    @Override
    protected IndexHandle createHandle (Integer key) {
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(INDICES_PER_VERTEX * key);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < key; j++) {
                int offset = (i * key + j) * 4;

                indicesBuffer.put(offset);
                indicesBuffer.put(offset + 1);
                indicesBuffer.put(offset + 2);
                indicesBuffer.put(offset + 2);
                indicesBuffer.put(offset + 3);
                indicesBuffer.put(offset);
            }
        }

        indicesBuffer.flip();

        int indicesBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        IndexHandle handle = new BasicIndexHandle();
        handle.setIndexId(indicesBufferId);
        return handle;
    }

    @Override
    protected void destroyHandle(Integer key) {
        IndexHandle handle = handleMap.remove(key);
        GL15.glDeleteBuffers(handle.getIndexId());

    }
}
