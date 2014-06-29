package org.supply.simulator.display.window;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfo;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Window<S extends ShaderEngine> extends SupplyRenderable {

    public void setShaderEngine(S shaderEngine);


}
