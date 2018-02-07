package org.supply.simulator.display.factory;

import org.apache.logging.log4j.Logger;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicEntity;
import org.supply.simulator.display.MenuFactory;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.logging.HasLogger;
import org.supply.simulator.util.FactoryUtils;
import org.supply.simulator.util.TextureUtils;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Brandon on 5/8/2016.
 */
public class TextMenuFactory extends HasLogger {
    private final float topLeftX, topLeftY, length, width;
    private final String text;
    private TextureEngine textureEngine;

    @Deprecated
    public TextMenuFactory(float topLeftX, float topLeftY, float length, float width, String text) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.length = length;
        this.width = width;
        this.text = text;
    }

    public TextMenuFactory(String text, float topLeftX, float topLeftY, float length, float width) {
        this.text = text;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.length = length;
        this.width = width;
    }

    public Entity build() {
        BasicEntity entity = new BasicEntity();

        final Set<Positions> positions = new LinkedHashSet<>();
        entity.setPositions(positions);

        for(int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Positions p = toPosition(i, c);
            positions.add(p);

            logger.debug(c.toString());
        }

        TextureUtils.applyTexture(entity, textureEngine);

        return entity;
    }

    private Positions toPosition(int i, Character c) {
        return FactoryUtils.newTexturedColorPositions(c.toString(), topLeftX+i*width, topLeftY, 0, length, width);
    }

    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
