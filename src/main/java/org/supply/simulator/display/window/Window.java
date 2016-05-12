package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;
import org.supply.simulator.display.core.SupplyDisplay;

/**
 * Interface for a game window.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Window extends SupplyDisplay {

    /**
     * Sets the shader engine this window will use.
     *
     * @param shaderEngine the shader engine
     */
    public void setShaderEngine(BasicShaderEngine shaderEngine);


}
