package org.supply.simulator.display.manager.chunk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/7/2014.
 */
public interface ChunkIndexManager {

    /**
     *
     * @param rows
     * @param columns
     * @return
     */
    public List<Integer> createIndicesBufferData(int rows, int columns);

    /**
     *
     * @param rows
     * @param columns
     * @return
     */
    public boolean isIndicesBufferIdStored (int rows, int columns);

    /**
     *
     * @param rows
     * @param columns
     * @param indicesBufferId
     */
    public void storeIndicesBufferId(int rows, int columns, int indicesBufferId);

    /**
     *
     * @param rows
     * @param columns
     * @return
     */
    public int getIndicesBufferId(int rows, int columns);
}
