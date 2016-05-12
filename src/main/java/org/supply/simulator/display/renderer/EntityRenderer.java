package org.supply.simulator.display.renderer;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;

import java.util.Collection;

/**
 * Created by Alex on 9/7/2014.
 */
public interface EntityRenderer<V extends Entity>{

    public void build(Collection<V> renderables);

    public void render(Collection<V> renderables);

    public void destroy(Collection<V> renderables);

    public void destroyAll();

    void setTextureEngine(BasicTextureEngine textureEngine);

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
    int[] getAttributeLocations();

    void setIndexEngine(BasicIndexEngine indexEngine);
}
