package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.BasicShaderEngine;

/**
 * Interface for a game window.
 *
 * Created by Alex on 6/17/2014.
 */
public interface Window {

    /**
     * Sets the shader engine this window will use.
     *
     * @param shaderEngine the shader engine
     */
    public void setShaderEngine(BasicShaderEngine shaderEngine);

    public void start();

    public void update();

    public void stop();


}
