package org.supply.simulator.display.renderable.menu.impl;


import org.supply.simulator.data.attribute.entity.MenuType;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Menu;
import org.supply.simulator.display.assetengine.texture.TextureHandle;
import org.supply.simulator.display.renderable.AbstractEntityRenderable;
import org.supply.simulator.display.renderable.menu.MenuRenderable;

/**
 * Created by Alex on 9/14/2014.
 */
public class BasicMenuRenderable extends AbstractEntityRenderable implements MenuRenderable {
    private Menu menu;
    private TextureHandle textureHandle;

    @Override
    public void setEntity(Entity entity) {
        this.menu = (Menu)entity;
    }

    @Override
    public MenuType getMenuType() {
        return menu.getType();
    }

    @Override
    public float[] getUnitPosition() {
        return menu.getPositions().getValue();
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
        if (!(o instanceof BasicMenuRenderable)) return -1;

        return textureHandle.compareTo(((BasicMenuRenderable) o).getTextureHandle());


    }
}
