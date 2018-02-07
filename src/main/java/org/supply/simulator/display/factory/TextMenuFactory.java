package org.supply.simulator.display.factory;

import org.apache.logging.log4j.Logger;
import org.supply.simulator.data.HasLogger;
import org.supply.simulator.data.attribute.entity.EntityAttribute;
import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.MenuFactory;
import org.supply.simulator.display.assetengine.texture.TextureEngine;
import org.supply.simulator.util.FactoryUtils;
import org.supply.simulator.util.TextureUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Brandon on 5/8/2016.
 */
public class TextMenuFactory implements HasLogger, MenuFactory {
    private final float topLeftX, topLeftY, length, width;
    private final String text;
    private TextureEngine textureEngine;
    private Logger logger = getLogger();



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


    @Override
    public Entity build() {
        return new Entity() {
            final Set<Positions> positions;

            {
                positions = new HashSet<>();
                for(int i = 0; i < text.length(); i++) {
                    Character c = text.charAt(i);
                    Positions p = FactoryUtils.newTexturedColorPositions(c.toString(), topLeftX+i*width, topLeftY, 0, length, width);
                    positions.add(p);

                    logger.debug(c.toString());
                }

                TextureUtils.applyTexture(this, textureEngine);
            }

            @Override
            public Set<Positions> getPositions() {
                return positions;
            }

            @Override
            public void setPositions(Set<Positions> positions) {
            }

            @Override
            public void addAttribute(EntityAttribute attribute) {

            }

            @Override
            public Long getId() {
//                return java.util.UUID.randomUUID();
                return null;
            }

//            @Override
            public void setId(Long id) {

            }
        };
    }

    public void setTextureEngine(TextureEngine textureEngine) {
        this.textureEngine = textureEngine;
    }
}
