package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.supplyrenderable.HasSize;

import java.nio.Buffer;

/**
 * Created by Alex on 6/17/2014.
 */
public interface ChunkData<V extends Buffer, C extends Buffer, I extends Buffer> extends HasSize {

    /**
     *
     * @return
     */
    V getPositionsBuffer();

    /**
     *
     * @return
     */
    C getColorBuffer();

    /**
     *
     * @return
     */
    I getIndicesBuffer();

    /**
     *
     * @param buf
     */
    void setPositionsBuffer(V buf);

    /**
     *
     * @param buf
     */
    void setColorBuffer(C buf);

    /**
     *
     * @param buf
     */
    void setIndicesBuffer(I buf);
}
