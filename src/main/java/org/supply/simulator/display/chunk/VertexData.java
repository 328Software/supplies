package org.supply.simulator.display.chunk;

import org.supply.simulator.display.renderableinfo.HasSize;

import java.nio.Buffer;

/**
 * Created by Alex on 6/17/2014.
 */
public interface VertexData<V extends Buffer, C extends Buffer> extends HasSize {

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
     * @param buf
     */
    void setPositionsBuffer(V buf);

    /**
     *
     * @param buf
     */
    void setColorBuffer(C buf);
}
