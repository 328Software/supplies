package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.util.Renderable;

/**
 * Interface for all objects that will be rendered by the Display Engine. All OpenGl code will be in classes that 
 * implement this interface(with the exception of the ShaderEngine).
 *
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
