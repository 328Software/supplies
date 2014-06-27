package org.supply.simulator.display.chunk.impl;

import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.chunk.ChunkManager;
import org.supply.simulator.display.window.Camera;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public class ChunkManagerBasic<K,V extends Chunk>
        implements ChunkManager<K,V>, Iterator<V>{

    private HashMap<K,V> chunks;
    private ArrayList<K> chunkIds;
    private int iteratorCount;

    private int VIEWDISTANCE=100;

    public ChunkManagerBasic () {
        chunks = new HashMap<>();
        chunkIds = new ArrayList<>();
        iteratorCount=0;
    }

    @Override
    public void updateChunks(Camera view) {
        ArrayList<K> nextChunks = getViewableChunks(view);

        iteratorCount=0;

        // DELETE CHUNKS THAT HAVE LEFT RANGE
        for (K id:chunkIds) {
            if (!nextChunks.contains(id)) {
                chunkIds.remove(id);
                chunks.get(id).destroy();
                chunks.remove(id);
            }
        }

        // CREATE CHUNKS THAT HAVE ENTERED RANGE
        for (K id:nextChunks) {
            if (!chunkIds.contains(id)) {
                chunkIds.add(id);
                V chunk = getChunkFromDAO(id);
                chunk.build();
            }
        }

    }

    @Override
    public boolean hasNext() {
        return iteratorCount<chunkIds.size();
    }

    @Override
    public V next() {
        if (iteratorCount>=chunkIds.size()) {
            iteratorCount++;
            return chunks.get(chunkIds.get(iteratorCount-1));
        } else {
            return null;
        }
    }

    @Override
    public void remove() {

    }

    private V getChunkFromDAO(K chunkId) {
        //TODO interface with DAO
        V chunk = null;
        return chunk;
    }

    private ArrayList<K> getViewableChunks(Camera view) {
        //TODO Badass algorithm here
        return new ArrayList<K>();
    }

}

