package org.supply.simulator.display.assetengine.indices;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexHandle;
import org.supply.simulator.logging.HasLogger;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public abstract class AbstractChunkIndexEngine<K,V extends ChunkIndexHandle>
        extends HasLogger
        implements ChunkIndexEngine<K,ChunkIndexHandle> {

    protected HashMap<K,ChunkIndexHandle> bufferIdMap;

    public AbstractChunkIndexEngine() {
        bufferIdMap = new HashMap<>();
    }

    @Override
    public ChunkIndexHandle get(K key) {
        ChunkIndexHandle handle = new BasicChunkIndexHandle();

        if (!bufferIdMap.containsKey(key)) {
            logger.error("Unknown chunk type", key);

        } else if (bufferIdMap.get(key).getIndicesId()==-1) {

            List<Integer> indicesBufferData=getIndicesBufferData().getData();

            int indicesBufferId = -1;

            IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.size());
            for(Integer i: indicesBufferData) {
                indicesBuffer.put(i);
            }

            indicesBuffer.flip();


            indicesBufferId = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

            handle.setIndicesId(indicesBufferId);
        }


        return handle;
    }

    //TODO Remove List and make generic
    protected abstract ChunkIndexData<List<Integer>> getIndicesBufferData();
}
