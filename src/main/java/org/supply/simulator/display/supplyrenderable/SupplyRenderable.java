package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.util.Renderable;

/**
 * Created by Alex on 6/29/2014.
 */
public interface SupplyRenderable  extends HasRenderableInfo, Renderable {

    /**
     *
     */
    public void build();

    /**
     *
     * @return
     */
    public boolean isBuilt();

    /**
     *
     */
    public void destroy();

    /**
     *
     * @return
     */
    public boolean isDestroyed();




}
