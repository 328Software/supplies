package org.supply.simulator.display.assetengine.indices.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.supply.simulator.data.attribute.entity.ChunkType;
import org.supply.simulator.display.assetengine.AbstractAssetEngine;
import org.supply.simulator.display.assetengine.indices.IndexHandle;
import org.supply.simulator.display.assetengine.indices.IndexEngine;

import java.nio.IntBuffer;

/**
 * Created by Alex on 7/7/2014.
 */
public class ChunkIndexEngine
        extends AbstractAssetEngine<ChunkType,IndexHandle>
        implements IndexEngine<ChunkType> {

        //, InitializingBean {

    private static final int INDICES_PER_VERTEX = 6;

//   ChunkTypeDAO chunkTypeDAO;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        for(ChunkType chunkType: chunkTypeDAO.findAll()) {
//            int[] indicesBufferData = getIndicesBufferData(chunkType).getData();
//            handleMap.put(chunkType,createBufferForIndices(indicesBufferData));
//        }
//    }

    public ChunkIndexEngine() {
        super();
    }

    @Override
    protected IndexHandle createHandle (ChunkType key) {
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


//        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.length);
//        for(Integer i: indicesBufferData) {
//            indicesBuffer.put(i);
//        }
;
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
    protected void destroyHandle(ChunkType key) {
        IndexHandle handle = handleMap.remove(key);
        GL15.glDeleteBuffers(handle.getIndexId());

    }


//    private int[] createTriangleIndicesData(int rows, int columns) {
//        int[] values = new int[rows*columns*INDICES_PER_VERTEX];
//
//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < columns; j++) {
//                int offset = (i* columns +j)*4;
//                values[i*columns+j]=offset  ;
//                values[i*columns+j]=offset+1;
//                values[i*columns+j]=offset+2;
//                values[i*columns+j]=offset+2;
//                values[i*columns+j]=offset+3;
//                values[i*columns+j]=offset  ;
//
//                //        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indicesBufferData.length);
////        for(Integer i: indicesBufferData) {
////            indicesBuffer.put(i);
////        }
//
//                values.put(offset);
//                values.put(offset+1);
//                values.put(offset+2);
//                values.put(offset+2);
//                values.put(offset+3);
//                values.put(offset);
//
////                values.add(offset);
////                values.add(offset+1);
////                values.add(offset+2);
////                values.add(offset+2);
////                values.add(offset+3);
////                values.add(offset);
//            }
//        }
//        return values;
//    }


    }
