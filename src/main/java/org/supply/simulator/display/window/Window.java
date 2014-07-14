package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * Interface for a game window.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Window extends SupplyRenderable {

    /**
     * Sets the shader engine this window will use.
     *
     * @param shaderEngine
     */
    public void setShaderEngine(ShaderEngine shaderEngine);

}
