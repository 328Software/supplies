package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.window.Camera;
import org.supply.simulator.logging.HasLogger;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public abstract class AbstractChunkManager<V extends Chunk>
        extends HasLogger
        implements ChunkManager<V> {

    protected Collection<V> chunkCollection;

    protected ChunkIndexManager indexManager;

    @Override
    public void update(Camera view) {

        logger.trace("***UPDATE CHUNKS");
        logger.trace("<<<<<<<<<<<<PRINT_CAMERA");
        logger.trace("Camera angle: " + view.getCameraAngle());
        logger.trace("Camera pos:   " + view.getCameraPos());
        logger.trace("Model pos:    " + view.getModelPos());
        logger.trace("Model angle:  " + view.getModelAngle());
        logger.trace("Model scale:  " + view.getModelScale());
        logger.trace(">>>>>>>>>>>>");

        updateChunks(view);

        for (V chunk: chunkCollection) {
            if (!chunk.isBuilt()) {
                chunk.setIndexManager(indexManager);
                chunk.build();
            }
        }


    }



    @Override
    public int size() {
        return chunkCollection.size();
    }

    @Override
    public boolean isEmpty() {
        return chunkCollection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return chunkCollection.contains(o);
    }

    @Override
    public Object[] toArray() {
    return chunkCollection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) chunkCollection.toArray();
        return a;
    }

    @Override
    public void clear() {
        for (V chunk: chunkCollection) {
            chunk.destroy();
        }
        chunkCollection.clear();
    }

    @Override
    public Iterator<V> iterator() {
        return chunkCollection.iterator();
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

    /**
     * Updates the chunk collection to chunks that are in view
     *
     * @param view the current camera view
     */
    protected abstract void updateChunks(Camera view);


}

