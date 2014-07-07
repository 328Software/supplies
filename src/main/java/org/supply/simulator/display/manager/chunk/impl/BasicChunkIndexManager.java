package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.display.manager.chunk.ChunkIndexManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 7/7/2014.
 */
public class BasicChunkIndexManager implements ChunkIndexManager {
    private HashMap<RowColPair,Integer> bufferIdMap;

    public BasicChunkIndexManager () {
        bufferIdMap = new HashMap<>();
    }

    @Override
    public List<Integer> createIndicesBufferData(int rows, int columns) {
        List<Integer> values = new ArrayList<Integer>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                int offset = (i* columns +j)*4;
                values.add(offset);
                values.add(offset+1);
                values.add(offset+2);
                values.add(offset+2);
                values.add(offset+3);
                values.add(offset);
            }
        }
        return values;
    }

    @Override
    public boolean isIndicesBufferIdStored(int rows, int columns) {
        return bufferIdMap.containsKey(new RowColPair(rows,columns));
    }

    @Override
    public void storeIndicesBufferId(int rows, int columns, int indicesBufferId) {
        bufferIdMap.put(new RowColPair(rows,columns),indicesBufferId);
    }

    @Override
    public int getIndicesBufferId(int rows, int columns) {
        return bufferIdMap.get(new RowColPair(rows,columns));
    }

    private class RowColPair {
        public Integer rows;
        public Integer columns;

        public RowColPair (int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }
    }
}
