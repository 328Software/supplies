package org.supply.simulator.display.assetengine.indices;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.AssetEngine;

import java.nio.IntBuffer;

/**
 * Created by Alex on 5/8/2016.
 */
public abstract class AbstractIndexEngine<K> extends AbstractAssetEngine<K,IndexHandle> implements AssetEngine<K,IndexHandle> {

    protected static final int INDICES_PER_VERTEX = 6;

    protected int createIndicesId(int rows, int columns) {
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(INDICES_PER_VERTEX * rows * columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int offset = (i * columns + j) * 4;

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

        return indicesBufferId;
    }
}
