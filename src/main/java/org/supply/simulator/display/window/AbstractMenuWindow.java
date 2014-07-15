package org.supply.simulator.display.window;

import org.supply.simulator.display.assetengine.shader.ShaderEngine;
import org.supply.simulator.display.assetengine.shader.ShaderHandle;
import org.supply.simulator.display.assetengine.shader.ShaderProgramType;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.supplyrenderable.AbstractSupplyRenderable;

/**
 * Created by Alex on 7/15/2014.
 */
public class AbstractMenuWindow extends AbstractSupplyRenderable implements Window {

    protected ShaderEngine<ShaderProgramType,ShaderHandle> shaderEngine;

    protected TextureEngine<String,TextureHandle> textureEngine;




    @Override
    public void build() {

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void render() {

    }

    @Override
    public void setShaderEngine(ShaderEngine shaderEngine) {
        this.shaderEngine = shaderEngine;
    }


    @Override
    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
