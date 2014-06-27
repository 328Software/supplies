package org.supply.simulator.display.chunk;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.renderableinfo.HasRenderableInfo;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Chunk<T extends VertexData> extends Renderable, HasRenderableInfo {

    /**
     *
     */
    public void build();

    /**
     *
     */
    public void destroy();

}
