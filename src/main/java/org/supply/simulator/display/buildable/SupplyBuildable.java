package org.supply.simulator.display.buildable;

import org.supply.simulator.display.renderable.SupplyRenderable;

/**
 * Created by Alex on 7/17/2014.
 */
public interface SupplyBuildable {
    /**
     * Prepares object to be rendered. Called once before rendering begins.
     *
     */
    public SupplyRenderable build();

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
