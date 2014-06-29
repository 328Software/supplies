package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.manager.chunk.Chunk;
import org.supply.simulator.display.manager.chunk.ChunkManager;
import org.supply.simulator.display.window.Camera;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public abstract class AbstractChunkManager<K,V extends Chunk>
        implements ChunkManager<K,V> {

    private HashMap<K,V> chunks;
    private ArrayList<K> chunkIds;
    private int iteratorCount;

    private int VIEWDISTANCE=100;

    public AbstractChunkManager() {
        chunks = new HashMap<>();
        chunkIds = new ArrayList<>();
        iteratorCount=-1;
    }

    @Override
    public void update(Camera view) {
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
                V chunk = getChunk(id);
                chunk.build();
                chunks.put(id, chunk);
            }
        }

    }

    @Override
    public int size() {
        return chunks.size();
    }

    @Override
    public boolean isEmpty() {
        return chunks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return chunks.containsKey(o);
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
    return chunks.values().toArray();
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



    protected abstract V getChunk(K chunkId);

    private ArrayList<K> getViewableChunks(Camera view) {
        //TODO Badass algorithm here
        return new ArrayList<K>();
    }

}

