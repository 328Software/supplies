package org.supply.simulator.display.renderer;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.display.assetengine.indices.BasicIndexEngine;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;

import java.util.Collection;

/**
 * Created by Alex on 9/7/2014.
 */
public interface EntityRenderer {

    public void build(Collection<Entity> renderables);

    public void render(Collection<Entity> renderables);

    public void destroy(Collection<Entity> renderables);

    public void destroyAll();

    void setTextureEngine(TextureEngine textureEngine);

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
