package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.renderable.SupplyRenderable;

/**
 * Interface for a game window.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Window extends SupplyRenderable {

    /**
     * Sets the shader engine this window will use.
     *
     * @param shaderEngine the shader engine
     */
    public void setShaderEngine(ShaderEngine shaderEngine);

    /**
     * Sets the texture engine this window will use.
     *
     * @param textureEngine the texture engine
     */
    public void setTextureEngine(TextureEngine textureEngine);

}
