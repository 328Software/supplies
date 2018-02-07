package org.supply.simulator.display.factory;

import org.supply.simulator.Builder;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.logging.HasLogger;
import org.supply.simulator.util.FactoryUtils;

/**
 * Created by Brandon on 2/7/2018.
 */
public abstract class MenuSubElementBuilder extends HasLogger implements Builder<Entity> {
    protected final float topLeftX;
    protected final float topLeftY;
    protected final float length;
    protected final float width;
    protected TextureEngine textureEngine;

    public MenuSubElementBuilder(float length, float topLeftX, float topLeftY, float width) {
        this.length = length;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
    }

    protected Positions toPosition(int i, Character c) {
        return FactoryUtils.newTexturedColorPositions(c.toString(), topLeftX+i*width, topLeftY, 0, length, width);
    }

    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
