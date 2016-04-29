package org.supply.simulator.display.renderable.menu;

import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderable.EntityRenderable;

/**
 * Created by Alex on 9/14/2014.
 */
public interface MenuRenderable extends EntityRenderable, Comparable {

    public MenuType getMenuType();

    public float[] getUnitPosition();

    public TextureHandle getTextureHandle();

    public void setTextureHandle(TextureHandle textureHandle);
}
