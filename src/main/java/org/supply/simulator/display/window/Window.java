package org.supply.simulator.display.window;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.supplyrenderable.HasRenderableInfo;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.supplyrenderable.SupplyRenderable;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Window extends SupplyRenderable {

    public void setShaderEngine(ShaderEngine shaderEngine);

    public void setCamera(Camera camera);

    public void setChunkManager(Manager manager);

    public void setEntityManager(Manager manager);


}
