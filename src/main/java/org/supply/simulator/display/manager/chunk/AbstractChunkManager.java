package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.assetengine.indices.ChunkIndexEngine;
import org.supply.simulator.display.window.Camera;
import org.supply.simulator.logging.HasLogger;

import java.util.*;

/**
 * Created by Alex on 6/17/2014.
 */
public abstract class AbstractChunkManager<V extends ChunkRenderable>
        extends HasLogger
        implements ChunkManager<V> {

    protected Collection<V> visibleChunks;


    protected ChunkIndexEngine<ChunkType> indexEngine;

    @Override
    public void update(Camera view) {

        ////////////ADD CHUNKS
        //get chunks that NEED TO BE RENDERED
        Collection<V> renderables = getChunksToAdd(view);

        for (V chunkRenderable: renderables) {
            chunkRenderable.setIndicesBufferId(indexEngine.get(chunkRenderable.getChunkType()));
        }

        addAll(renderables);
        ////////////


        ////////////REMOVE CHUNKS
        //todo - what mechanism signals what chunks should be destroyed? the camera? can it take a chunk and tell return whether or not it is in frame? whose responsibility is it?
        renderables = getChunksToRemove(view);

        for (V chunkRenderable: renderables) {
            chunkRenderable.destroy();
        }

        removeAll(renderables);
        ////////////



    }




    @Override
    public int size() {
        return visibleChunks.size();
    }

    @Override
    public boolean isEmpty() {
        return visibleChunks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return visibleChunks.contains(o);
    }

    @Override
    public Object[] toArray() {
    return visibleChunks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        a = (T[]) visibleChunks.toArray();
        return a;
    }

    @Override
    public void clear() {
        for (V chunk: visibleChunks) {
            chunk.destroy();
        }
        visibleChunks.clear();
    }

    @Override
    public Iterator<V> iterator() {
        return visibleChunks.iterator();
    }

    @Override
    public boolean add(V v) {
        return visibleChunks.add(v);
    }

    @Override
    public boolean remove(Object o) {
        return visibleChunks.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return visibleChunks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return visibleChunks.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return visibleChunks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return visibleChunks.retainAll(c);
    }

    /**
     * Updates the chunk collection to chunks that are in view
     *
     * @param view the current camera view
     */
    protected abstract Collection<V> getChunksToAdd(Camera view);

    protected abstract Collection<V> getChunksToRemove(Camera view);

    @Override
    public void setIndexEngine(ChunkIndexEngine indexEngine) {
        this.indexEngine = indexEngine;
    }


}

