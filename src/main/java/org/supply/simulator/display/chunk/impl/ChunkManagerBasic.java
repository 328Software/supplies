package org.supply.simulator.display.chunk.impl;

import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.chunk.ChunkManager;
import org.supply.simulator.display.chunk.VertexData;
import org.supply.simulator.display.window.Camera;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public class ChunkManagerBasic<K,V extends Chunk,T,S>
        implements ChunkManager<K,ChunkBasic<T>,VertexDataBasic,HashMap<K,ChunkBasic<T>>>, Iterator{

    private HashMap<K,ChunkBasic<T>> chunks;
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
        ArrayList<K> nextChunks = getViewAbleChunks(view);


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
                Chunk<VertexDataBasic> chunk = new ChunkBasic<>();
                chunk.build(getVertexDataFromDAO(id));
            }
        }

    }

    @Override
    public HashMap<K,ChunkBasic<T>> getChunks() {
        return chunks;
    }

    @Override
    public boolean hasNext() {
        return iteratorCount<chunkIds.size();
    }

    @Override
    public Object next() {
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

    private VertexDataBasic getVertexDataFromDAO (K chunkId) {
        //TODO interface with DAO
        VertexDataBasic<FloatBuffer,ByteBuffer> data = new VertexDataBasic<>();
        return data;
    }

    private ArrayList<K> getViewAbleChunks (Camera view) {
        //TODO Badass algorithm here
        return new ArrayList<K>();
    }

}

