package org.supply.simulator.display.window;

import org.lwjgl.util.Renderable;
import org.supply.simulator.display.renderableinfo.HasRenderableInfo;
import org.supply.simulator.display.shader.ShaderEngine;
import org.supply.simulator.display.shader.TextureEngine;

/**
 * Created by Alex on 6/17/2014.
 */
public interface Window<S extends ShaderEngine, T extends TextureEngine> extends Renderable, HasRenderableInfo {

    public void setShaderEngine(S shaderEngine);

    public void setTextureEngine(T textureEngine);



}
