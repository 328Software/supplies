package org.supply.simulator.display.assetengine.indices;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.logging.HasLogger;

import java.nio.IntBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public abstract class AbstractChunkIndexEngine<K extends ChunkType>
        extends HasLogger
        implements ChunkIndexEngine<K> {


    protected HashMap<K,Integer> bufferIdMap;

    public AbstractChunkIndexEngine() {
        bufferIdMap = new HashMap<>();
    }

    @Override
    public Integer get(K key) {

        if (!bufferIdMap.containsKey(key)) {
            ChunkIndexData<IntBuffer> indicesBufferData = getIndicesBufferData(key);
            bufferIdMap.put(key,createBufferForIndices(indicesBufferData));
        }
        return bufferIdMap.get(key);
    }

    protected Integer createBufferForIndices(ChunkIndexData<IntBuffer> indicesBufferData) {
        int indicesBufferId;

//        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.length);
//        for(Integer i: indicesBufferData) {
//            indicesBuffer.put(i);
//        }

        IntBuffer indicesBuffer = indicesBufferData.getData();
        indicesBuffer.flip();

        indicesBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return indicesBufferId;
    }

    protected abstract ChunkIndexData getIndicesBufferData(K key);
}
