package org.supply.simulator.display.manager.chunk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7/7/2014.
 */
public interface ChunkIndexManager {

    public List<Integer> createIndicesBufferData(int rows, int columns);

    public boolean isIndicesBufferIdStored (int rows, int columns);

    public void storeIndicesBufferId(int rows, int columns, int indicesBufferId);

    public int getIndicesBufferId(int rows, int columns);
}
