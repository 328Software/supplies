package org.supply.simulator.display.manager.chunk.impl;

import org.supply.simulator.display.manager.chunk.ChunkData;
import org.supply.simulator.display.supplyrenderable.HasSize;
import org.supply.simulator.display.supplyrenderable.HasSizeAbstract;

import java.nio.Buffer;

/**
 * Created by Alex on 6/17/2014.
 */
public class BasicChunkData<V extends Buffer,C extends Buffer> extends HasSizeAbstract implements ChunkData<V,C>, HasSize {

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


    @Override
    public V getPositionsBuffer() {
        return this.positions;
    }

    @Override
    public C getColorBuffer() {
        return this.colors;
    }

    @Override
    public void setPositionsBuffer(V buf) {
        positions = buf;
    }

    @Override
    public void setColorBuffer(C buf) {
        colors = buf;
    }
}
