package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;

/**
 * Created by Alex on 6/29/2014.
 */

public class BasicChunkManager<K,V extends Chunk> extends AbstractChunkManager<K,V> {
    public BasicChunkManager() {
         super();
    }

    @Override
    protected V getChunk(K chunkId) {
        //todo implement
        return null;
    }

    @Override
    protected ArrayList<K> getViewableChunks(Camera view) {
        //todo implement
        return null;
    }
}
