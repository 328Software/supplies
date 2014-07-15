package org.supply.simulator.display.assetengine.texture.impl;

import org.supply.simulator.display.assetengine.texture.TextureHandle;

/**
 * Created by Alex on 7/14/2014.
 */
public class BasicTextureHandle implements TextureHandle {
    private Integer textureId;

    @Override
    public Integer getTextureId() {
        return this.textureId;
    }

    @Override
    public void setTextureId(Integer textureId) {
        this.textureId = textureId;
    }
}
