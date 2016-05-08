package org.supply.simulator.data.attribute.entity;

import org.supply.simulator.display.assetengine.texture.TextureHandle;

/**
 * Created by Alex on 9/14/2014.
 */
public interface EntityType<I> extends EntityAttribute<I> {

    //TODO PRETTY SURE ENTITIES don't NEED TO KNOW THEIR TEXTURE HANDLES ANYMORE
    public void setTextureHandle(TextureHandle textureHandle);

    public TextureHandle getTextureHandle();
}
