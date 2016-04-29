package org.supply.simulator.display.renderable.unit.impl;

import org.supply.simulator.data.attribute.entity.UnitType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Unit;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderable.AbstractEntityRenderable;
import org.supply.simulator.display.renderable.unit.UnitRenderable;

/**
 * Created by Alex on 7/20/2014.
 */
public class BasicUnitRenderable extends AbstractEntityRenderable  implements UnitRenderable {
    private Unit unit;
    private TextureHandle textureHandle;

    @Override
    public void setEntity(Entity entity) {
        this.unit = (Unit)entity;
    }

    @Override
    public UnitType getUnitType() {
        return unit.getType();
    }

    @Override
    public float[] getUnitPosition() {
        return unit.getUnitPositions().getValue();
    }

    @Override
    public TextureHandle getTextureHandle() {
        return textureHandle;
    }

    @Override
    public void setTextureHandle(TextureHandle textureHandle) {
        this.textureHandle = textureHandle;
    }

    @Override
    public int compareTo (Object o) {
        if (this == o) return 0;
        if (!(o instanceof BasicUnitRenderable)) return -1;

        return textureHandle.compareTo(((BasicUnitRenderable) o).getTextureHandle());


    }
}
