package org.supply.simulator.display.renderable;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.buildable.SupplyBuildable;

/**
 * Created by Alex on 7/13/2014.
 */
public interface SupplyRenderable extends Renderable{

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
