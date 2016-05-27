package org.supply.simulator.display.assetengine.indices;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.display.assetengine.AssetEngine;
import org.supply.simulator.display.assetengine.WeakReferenceEngine;
import org.supply.simulator.util.MapUtils;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 7/7/2014.
 */
public class BasicIndexEngine
        extends WeakReferenceEngine<Map.Entry<Integer, Integer>, IndexHandle>
        implements AssetEngine<Map.Entry<Integer, Integer>, IndexHandle> {

    private static final int INDICES_PER_VERTEX = 6;
    private Map<Map.Entry<Integer, Integer>, Integer> indexIdMap = new HashMap<>();


    public BasicIndexEngine() {
        super();
//        handleMap= MapUtils.newMultiKeyMap();
    }

    @Override
    protected IndexHandle createHandle(Map.Entry<Integer, Integer> key) {
        IndexHandle handle = new IndexHandle();
        Integer indicesId = createIndicesId(key.getKey(), key.getValue());
        handle.setIndexId(indicesId);
        indexIdMap.put(key, indicesId);
        return handle;
    }

    @Override
    protected void destroyHandle(Map.Entry<Integer, Integer> key) {
        GL15.glDeleteBuffers(indexIdMap.get(key));
        indexIdMap.remove(key);
    }


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
