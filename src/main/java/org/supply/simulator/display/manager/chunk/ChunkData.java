package org.supply.simulator.display.manager.chunk;

import org.supply.simulator.display.supplyrenderable.HasSize;

import java.nio.Buffer;

/**
 * Created by Alex on 6/17/2014.
 */
public interface ChunkData<V, C, I> extends HasSize {

    /**
     *
     * @return
     */
    V getPositions();

    /**
     *
     * @return
     */
    C getColors();

    /**
     *
     * @return
     */
    I getIndices();

    /**
     *
     * @param buf
     */
    void setPositions(V buf);

    /**
     *
     * @param buf
     */
    void setColors(C buf);

    /**
     *
     * @param buf
     */
    void setIndices(I buf);
}
