package org.supply.simulator.display.renderable.unit;

import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderable.EntityRenderable;

/**
 * Created by Alex on 7/20/2014.
 */
public interface UnitRenderable extends EntityRenderable, Comparable {

    public UnitType getUnitType();

    public float[] getUnitPosition();

    public TextureHandle getTextureHandle();

    public void setTextureHandle(TextureHandle textureHandle);
}
