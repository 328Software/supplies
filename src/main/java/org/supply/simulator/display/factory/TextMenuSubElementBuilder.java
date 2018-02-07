package org.supply.simulator.display.factory;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.data.entity.impl.BasicEntity;
import org.supply.simulator.util.TextureUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Brandon on 5/8/2016.
 */
public class TextMenuSubElementBuilder extends MenuSubElementBuilder {

    private final String text;

    public TextMenuSubElementBuilder(String text, float topLeftX, float topLeftY, float length, float width) {
        super(length, topLeftX, topLeftY, width);
        this.text = text;
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

}
