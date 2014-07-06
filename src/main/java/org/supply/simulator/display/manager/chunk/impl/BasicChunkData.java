package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.data.HasId;
import org.supply.simulator.display.manager.chunk.ChunkData;
import org.supply.simulator.display.supplyrenderable.HasSize;
import org.supply.simulator.display.supplyrenderable.HasSizeAbstract;


/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunkData<V,C,I>
        extends HasSizeAbstract
        implements ChunkData<V,C,I>, HasSize, HasId<Long> {

    private long id;

    // The amount of bytes an element has
    public static final int POSITION_ELEMENT = 4 ;
    public static final int COLOR_ELEMENT = 1;

    // Elements per parameter
    public static final int POSITION_COUNT = 4;
    public static final int COLOR_COUNT = 4;
    public static final int TEXTURE_COUNT = 2;

    // Bytes per parameter
    public static final int POSITION_BYTES = POSITION_COUNT * POSITION_ELEMENT;
    public static final int COLOR_BYTES = COLOR_COUNT * COLOR_ELEMENT;
    public static final int TEXTURE_BYTE = TEXTURE_COUNT * POSITION_ELEMENT;

    // Byte offsets per parameter
    public static final int POSITION_BYTE_OFFSET = 0;
    public static final int COLOR_BYTE_OFFSET = POSITION_BYTE_OFFSET + POSITION_COUNT;
    public static final int TEXTURE_BYTE_OFFSET = COLOR_BYTE_OFFSET + COLOR_COUNT;

    private C colors;
    private V positions;
    private I indices;


    @Override
    public V getPositions() {
        return this.positions;
    }

    @Override
    public C getColors() {
        return this.colors;
    }

    @Override
    public I getIndices() {
        return this.indices;
    }

    @Override
    public void setPositions(V buf) {
        positions = buf;
    }

    @Override
    public void setColors(C buf) {
        colors = buf;
    }

    @Override
    public void setIndices(I buf) {
        indices = buf;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
