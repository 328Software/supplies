package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;

/**
 * Created by Alex on 6/29/2014.
 */
public class BasicChunkManager<K,V extends Chunk> extends AbstractChunkManager<K,V> {
    @Override
    protected V getChunk(K chunkId) {
        //todo implement
        return null;
    }
}
