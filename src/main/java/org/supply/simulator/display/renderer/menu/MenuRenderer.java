package org.supply.simulator.display.renderer.menu;

import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.display.renderable.menu.MenuRenderable;
import org.supply.simulator.display.renderer.EntityRenderer;

/**
 * Created by Alex on 9/14/2014.
 */
public interface MenuRenderer extends EntityRenderer<MenuRenderable> {

/**
 *
 * @param textureEngine
 */
public void setTextureEngine(TextureEngine textureEngine);


}
