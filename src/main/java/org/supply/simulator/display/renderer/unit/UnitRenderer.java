package org.supply.simulator.display.renderer.unit;

import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.renderable.unit.UnitRenderable;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 9/7/2014.
 */
public interface UnitRenderer extends EntityRenderer<UnitRenderable> {

    /**
     *
     * @param textureEngine
     */
    public void setTextureEngine(TextureEngine textureEngine);

}
