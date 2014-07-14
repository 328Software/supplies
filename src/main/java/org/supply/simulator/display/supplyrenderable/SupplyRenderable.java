package org.supply.simulator.display.supplyrenderable;

import org.lwjgl.util.Renderable;

/**
 * Created by Alex on 7/13/2014.
 */
public interface SupplyRenderable extends Renderable{
    /**
     * Prepares object to be rendered. Called once before rendering begins.
     *
     */
    public void build();

    /**
     * Checks if this object has been built yet.
     *
     * @return false until build() has been called successfully
     */
    public boolean isBuilt();

    /**
     * Deletes all information this object stored in OpenGl buffers. Can be called once after the build() method has
     * been called. This must be called before allowing this object to be destroyed by the garbage collector, or else
     * there would be a memory leak with orphaned OpenGl buffers floating around.
     *
     */
    public void destroy();

    /**
     * Checks if this object has been destroyed yet.
     *
     * @return false until destroy() has been called
     */
    public boolean isDestroyed();

    /**
     * Sets the vertex shader attribute locations. These locations were created by the ShaderEngine and reference\
     * uniforms within shaders(glsl).
     *
     * @param locations vertex shader attribute locations
     */
    void setAttributeLocations(int[] locations);

    /**
     * Gets the vertex shader attribute locations. These locations were created by the ShaderEngine and reference\
     * uniforms within shaders(glsl)
     *
     * @return
     */
    int[] getAttributeLocations ();
}
