package org.supply.simulator.display.mock;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.display.assetengine.indices.ChunkIndexData;
import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexData;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.logging.HasLogger;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 7/14/2014.
 */
public class MockChunkIndexEngine extends HasLogger implements ChunkIndexEngine<ChunkType>{

    private static final int INDICES_PER_VERTEX = 6;

    protected HashMap<ChunkType,Integer> bufferIdMap;

    public MockChunkIndexEngine() {
        bufferIdMap = new HashMap<>();
    }

    public Integer get(ChunkType key) {

        if (!bufferIdMap.containsKey(key)) {



            IntBuffer indicesBuffer = BufferUtils.createIntBuffer(INDICES_PER_VERTEX * key.getRows() * key.getColumns());
            for (int i = 0; i < key.getRows(); i++) {
                for (int j = 0; j < key.getColumns(); j++) {
                    int offset = (i * key.getColumns() + j) * 4;

                    indicesBuffer.put(offset);
                    indicesBuffer.put(offset + 1);
                    indicesBuffer.put(offset + 2);
                    indicesBuffer.put(offset + 2);
                    indicesBuffer.put(offset + 3);
                    indicesBuffer.put(offset);
                }

            }


            bufferIdMap.put(key,createBufferForIndices(indicesBuffer));
        }
        return bufferIdMap.get(key);
    }

    private Integer createBufferForIndices(IntBuffer indicesBuffer) {
        int indicesBufferId;
        indicesBuffer.flip();

        indicesBufferId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return indicesBufferId;
    }


    private ChunkIndexData getIndicesBufferData(ChunkType key) {
        ChunkIndexData data = new BasicChunkIndexData();
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(INDICES_PER_VERTEX * key.getRows() * key.getColumns());
        for (int i = 0; i < key.getRows(); i++) {
            for (int j = 0; j < key.getColumns(); j++) {
                int offset = (i * key.getColumns() + j) * 4;

                indicesBuffer.put(offset);
                indicesBuffer.put(offset + 1);
                indicesBuffer.put(offset + 2);
                indicesBuffer.put(offset + 2);
                indicesBuffer.put(offset + 3);
                indicesBuffer.put(offset);
            }
        }

        data.setData(indicesBuffer);


        return data;
    }



}
