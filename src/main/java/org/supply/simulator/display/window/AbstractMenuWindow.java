package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.TextureEngine;

/**
 * Created by Alex on 7/15/2014.
 */
public class AbstractMenuWindow implements Window {

    protected ShaderEngine<ShaderProgramType> shaderEngine;

    protected TextureEngine textureEngine;


    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }

    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
