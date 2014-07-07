package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.window.Camera;
import org.supply.simulator.logging.HasLogger;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public abstract class AbstractChunkManager<K,V extends Chunk>
        extends HasLogger
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
        iteratorCount=0;
        if (!view.equals(lastView)) {

            ArrayList<K> nextChunks = getViewableChunks(view);


            logger.trace("***UPDATE CHUNKS");
            logger.trace("<<<<<<<<<<<<PRINT_CAMERA");
            logger.trace("Camera angle: " + view.getCameraAngle());
            logger.trace("Camera pos:   " + view.getCameraPos());
            logger.trace("Model pos:    " + view.getModelPos());
            logger.trace("Model angle:  " + view.getModelAngle());
            logger.trace("Model scale:  " + view.getModelScale());
            logger.trace(">>>>>>>>>>>>");


//            // DELETE CHUNKS THAT HAVE LEFT RANGE
//            for (K id:chunkIds) {
//                if (!nextChunks.contains(id)) {
//                    chunkIds.remove(id);
//                    chunks.get(id).destroy();
//                    chunks.remove(id);
//                }
//            }

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
    public Object[] toArray() {
    return chunks.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[])chunks.values().toArray();
        return a;
    }

    @Override
    public void clear() {
        for (K id:chunkIds) {
            chunks.get(id).destroy();
            chunks.remove(id);
        }
        chunkIds.clear();
    }

    /**
     * Abstract method to get chunk objects from the database.
     *
     * @param chunkId chunk Id
     * @return chunk object from db
     */
    protected abstract V getChunk(K chunkId);

    /**
     * (Private) method that uses the current camera to determine the chunks that are currently in view.
     *
     * @param view the Camera view
     * @return an ArrayList containing the ids of the viewable chunks
     */
    protected abstract ArrayList<K> getViewableChunks(Camera view);

    @Override
    public Iterator<V> iterator() {
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


}

