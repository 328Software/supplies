package org.supply.simulator.util;

import org.supply.simulator.data.entity.Entity;
import org.supply.simulator.data.entity.Positions;
import org.supply.simulator.display.assetengine.texture.BasicTextureEngine;
import org.supply.simulator.display.assetengine.texture.TextureHandle;

import static java.util.Objects.nonNull;

/**
 * Created by Brandon on 5/17/2016.
 */
public class TextureUtils {



    public static void applyTexture(Entity entity, BasicTextureEngine textureEngine) {
//        entity.setAtlas(textureEngine.get(entity.getTextureKey()).getAtlas());
        for(Positions pos : entity.getPositions()) {
            if(nonNull(pos.getTextureKey())) {
                entity.setAtlas(textureEngine.get(pos.getTextureKey()).getAtlas());
                TextureHandle texture = textureEngine.get(pos.getTextureKey());

//            Positions pos = entity.getPositions();

                //TODO can this be done on Entity generation?
                pos.getValue()[8] = texture.getSubInfo()[0] / texture.getAtlas().getWidth();  //X0
                pos.getValue()[9] = texture.getSubInfo()[1] / texture.getAtlas().getHeight(); //Y0

                pos.getValue()[18] = texture.getSubInfo()[0] / texture.getAtlas().getWidth();  //X0
                pos.getValue()[19] = texture.getSubInfo()[3] / texture.getAtlas().getHeight(); //Y1

                pos.getValue()[28] = texture.getSubInfo()[2] / texture.getAtlas().getWidth();  //X1
                pos.getValue()[29] = texture.getSubInfo()[3] / texture.getAtlas().getHeight(); //Y1

                pos.getValue()[38] = texture.getSubInfo()[2] / texture.getAtlas().getWidth();  //X1
                pos.getValue()[39] = texture.getSubInfo()[1] / texture.getAtlas().getHeight(); //Y0
            }
        }
    }
}
