package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.display.manager.chunk.AbstractChunkManager;
import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.window.Camera;

import java.util.ArrayList;

/**
 * Created by Alex on 6/29/2014.
 */

public class BasicChunkManager<V extends Chunk> extends AbstractChunkManager<V> {
    public BasicChunkManager() {
         super();
    }

    @Override
    protected void updateChunks(Camera view) {

    }

}
