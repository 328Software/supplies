package org.supply.simulator.data.attribute.entity;

import org.supply.simulator.display.assetengine.texture.TextureHandle;

/**
 * Created by Alex on 9/14/2014.
 */
public interface EntityType<I> extends EntityAttribute<I> {

    public void setTextureHandle(TextureHandle textureHandle);

    public TextureHandle getTextureHandle();
}
