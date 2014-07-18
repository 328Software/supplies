package org.supply.simulator.display.assetengine.indices.impl;

import org.springframework.beans.factory.InitializingBean;
import org.supply.simulator.core.dao.chunk.ChunkTypeDAO;
import org.supply.simulator.display.assetengine.indices.*;
import org.supply.simulator.display.manager.chunk.ChunkType;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/7/2014.
 */
public class BasicChunkIndexEngine
        extends AbstractChunkIndexEngine<ChunkType>
        implements ChunkIndexEngine<ChunkType>, InitializingBean {

    ChunkTypeDAO chunkTypeDAO;

    @Override
    public void afterPropertiesSet() throws Exception {
        for(ChunkType chunkType: chunkTypeDAO.findAll()) {
            List<Integer> indicesBufferData = getIndicesBufferData(chunkType).getData();
            chunkType.setIndicesBufferId(createBufferForIndices(indicesBufferData));
        }
    }

    public BasicChunkIndexEngine() {
        super();
    }

    @Override
    protected ChunkIndexData<List<Integer>> getIndicesBufferData(ChunkType key) {
        ChunkIndexData<List<Integer>> data = new BasicChunkIndexData<>();


        data.setData(createTriangleIndicesData(key.getRows(), key.getColumns()));


        return data;
    }




    private List<Integer> createTriangleIndicesData(int rows, int columns) {
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

    public class RowColPair {
        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        private int row;

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        private int col;


    }
}
