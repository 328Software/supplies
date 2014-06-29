package org.supply.simulator.display.chunk.impl;

import org.supply.simulator.display.chunk.Chunk;
import org.supply.simulator.display.chunk.ChunkManager;
import org.supply.simulator.display.window.Camera;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunkManager<K,V extends Chunk>
        implements ChunkManager<K,V> {

    private HashMap<K,V> chunks;
    private ArrayList<K> chunkIds;
    private int iteratorCount;

    private int VIEWDISTANCE=100;

    public BasicChunkManager() {
        chunks = new HashMap<>();
        chunkIds = new ArrayList<>();
        iteratorCount=-1;
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
                chunks.put(id, chunk);
            }
        }

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(V v) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    //    @Override
//    public boolean hasNext() {
//        return iteratorCount<chunkIds.size();
//    }
//
//    @Override
//    public V next() {
//        if (iteratorCount>=chunkIds.size()) {
//            iteratorCount++;
//            return chunks.get(chunkIds.get(iteratorCount));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public void remove() {
//
//    }



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

