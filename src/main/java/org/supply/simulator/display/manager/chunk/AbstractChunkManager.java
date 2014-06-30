package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.window.Camera;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public abstract class AbstractChunkManager<K,V extends Chunk>
        implements ChunkManager<K,V> {

    protected HashMap<K,V> chunks;
    protected ArrayList<K> chunkIds;
    protected int iteratorCount;

    private Camera lastView;

    private int VIEWDISTANCE=100;

    public AbstractChunkManager() {
        lastView = null;
        chunks = new HashMap<>();
        chunkIds = new ArrayList<>();
        iteratorCount=0;
    }

    @Override
    public void update(Camera view) {

        if (view.equals(lastView)) {

            ArrayList<K> nextChunks = getViewableChunks(view);

            System.out.println("***UPDATE CHUNKS");
            System.out.println("<<<<<<<<<<<<PRINT_CAMERA");
            System.out.println("Camera angle: " + view.getCameraAngle());
            System.out.println("Camera pos:   " + view.getCameraPos());
            System.out.println("Model pos:    " + view.getModelPos());
            System.out.println("Model angle:  " + view.getModelAngle());
            System.out.println("Model scale:  " + view.getModelScale());
            System.out.println(">>>>>>>>>>>>");

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
        lastView = view;

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
        a = (T[])chunks.values().toArray();
        return a;
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
        for (K id:chunkIds) {
            chunkIds.remove(id);
            chunks.get(id).destroy();
            chunks.remove(id);
        }
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

    protected abstract ArrayList<K> getViewableChunks(Camera view);

}

