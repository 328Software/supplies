package org.supply.simulator.display.assetengine.indices.impl;

import org.lwjgl.BufferUtils;
import org.springframework.beans.factory.InitializingBean;
import org.supply.simulator.core.dao.chunk.ChunkTypeDAO;
import org.supply.simulator.display.assetengine.indices.*;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkType;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/7/2014.
 */
public class BasicChunkIndexEngine
        extends AbstractChunkIndexEngine<ChunkType>
        implements ChunkIndexEngine<ChunkType> {

        //, InitializingBean {

    private static final int INDICES_PER_VERTEX = 6;

//   ChunkTypeDAO chunkTypeDAO;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        for(ChunkType chunkType: chunkTypeDAO.findAll()) {
//            int[] indicesBufferData = getIndicesBufferData(chunkType).getData();
//            bufferIdMap.put(chunkType,createBufferForIndices(indicesBufferData));
//        }
//    }

    public BasicChunkIndexEngine() {
        super();
    }

    @Override
    protected ChunkIndexData getIndicesBufferData(ChunkType key) {
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
