package org.supply.simulator.display.chunk;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.core.HasRenderableInfo;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Chunk<T> extends Renderable, HasRenderableInfo {

    /**
     *
     * @param data
     */
    public void build(T data);

    /**
     *
     */
    public void destroy();

}
